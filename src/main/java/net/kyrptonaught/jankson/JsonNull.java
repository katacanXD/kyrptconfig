/*
 * MIT License
 *
 * Copyright (c) 2018-2020 Falkreon (Isaac Ellingson)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.kyrptonaught.jankson;

import java.io.IOException;
import java.io.Writer;

public class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {
    }

    public String toString() {
        return "null";
    }

    @Override
    public boolean equals(Object other) {
        return other == JsonNull.INSTANCE;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toJson(boolean comments, boolean newlines, int depth) {
        return "null";
    }

    @Override
    public void toJson(Writer writer, JsonGrammar grammar, int depth) throws IOException {
        writer.write("null");
    }

    //IMPLEMENTATION for Cloneable
    @Override
    public JsonNull clone() {
        return this; //Technically violates the contract for Cloneable, but this is a singleton
    }
}
