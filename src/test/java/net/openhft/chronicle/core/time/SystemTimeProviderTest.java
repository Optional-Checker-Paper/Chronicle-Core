/*
 * Copyright 2016-2020 Chronicle Software
 *
 * https://chronicle.software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.core.time;

import net.openhft.chronicle.core.FlakyTestRunner;
import net.openhft.chronicle.core.Jvm;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SystemTimeProviderTest {
    @Test
    public void currentTimeMicros() {
        FlakyTestRunner.run(Jvm.isArm(), this::doCurrentTimeMicros);
    }

    public void doCurrentTimeMicros() {
        @NotNull TimeProvider tp = SystemTimeProvider.INSTANCE;
        long minDiff = 0;
        long maxDiff = 0;
        long lastTimeMicros;
        long start;

        for (int i = 0; i <= 20; i++) {
            minDiff = 10;
            maxDiff = 995;
            lastTimeMicros = 0;
            start = System.currentTimeMillis();

            do {
                long now0 = tp.currentTimeMillis();
                long time2 = tp.currentTimeMicros();
                long now1 = tp.currentTimeMillis();
                if (now1 - now0 > 0) {
                    if (now1 - now0 > 1)
                        System.out.println("jump: " + (now1 - now0));
                    continue;
                }

                long now = now1 * 1000;
                long diff = time2 - now;
                if (minDiff > diff) {
                    minDiff = diff;
                    System.out.println("min: " + minDiff);
                }
                if (maxDiff < diff) {
                    maxDiff = diff;
                    System.out.println("max: " + maxDiff);
                }
                Thread.yield();
                assertTrue(time2 >= lastTimeMicros);
                lastTimeMicros = time2;
            } while (System.currentTimeMillis() < start + 500);

            if (-2 <= minDiff && minDiff <= 0) {
                if (999 <= maxDiff && maxDiff <= 1001) {
                    System.out.println("minDiff: " + minDiff + ", maxDiff: " + maxDiff);
                    return;
                }
            }
        }
        assertEquals(-10, minDiff, 10);
        assertEquals(1000, maxDiff, 10);
    }

    @Test
    public void currentTime() {
        FlakyTestRunner.run(() -> {
            TimeProvider tp = SystemTimeProvider.INSTANCE;
            long time1 = tp.currentTime(TimeUnit.SECONDS);
            long time2 = tp.currentTimeMillis();
            long time3 = tp.currentTimeMicros();
            long time4 = tp.currentTimeNanos();
            assertEquals(time1, time2 / 1000 + 1, 1);
            assertEquals(time2, time3 / 1000 + 10, 10);
            assertEquals(time3, time4 / 1000 + 5000, 10000);
        });
    }
}