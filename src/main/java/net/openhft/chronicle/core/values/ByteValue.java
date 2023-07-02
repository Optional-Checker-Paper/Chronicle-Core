/*
 * Copyright 2016-2020 chronicle.software
 *
 *       https://chronicle.software
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

package net.openhft.chronicle.core.values;

/**
 * This interface represents a reference to a byte value. It provides methods to get, set, and add to the value.
 * Implementations of this interface are expected to handle the storage and retrieval of the byte value.
 * <p>
 * Implementations may store the value in different formats or mediums. For example, the value could be stored
 * in binary format or text format, in memory or on disk.
 * <p>
 * The getValue method retrieves the current byte value. The setValue method sets the byte value.
 * The addValue method adds a specified byte to the current value and returns the result.
 *
 * @see net.openhft.chronicle.core.values.BooleanValue
 * @see net.openhft.chronicle.core.values.ByteValue
 * @see net.openhft.chronicle.core.values.CharValue
 * @see net.openhft.chronicle.core.values.DoubleValue
 * @see net.openhft.chronicle.core.values.FloatValue
 * @see net.openhft.chronicle.core.values.IntValue
 * @see net.openhft.chronicle.core.values.LongValue
 * @see net.openhft.chronicle.core.values.ShortValue
 * @see net.openhft.chronicle.core.values.StringValue
 * @see net.openhft.chronicle.core.values.LongArrayValues
 * @see net.openhft.chronicle.core.values.IntArrayValues
 * @see net.openhft.chronicle.core.values.UnsetLongValue
 * @author Peter Lawrey
 * @since 10/10/13
 */
public interface ByteValue {
    /**
     * Retrieves the byte value.
     *
     * @return the byte value
     */
    byte getValue();

    /**
     * Sets the byte value.
     *
     * @param value the byte value to set
     */
    void setValue(byte value);

    /**
     * Adds a specified byte to the current value and returns the result.
     *
     * @param b the byte to add to the current value
     * @return the result of adding the specified byte to the current value
     */
    byte addValue(byte b);
}
