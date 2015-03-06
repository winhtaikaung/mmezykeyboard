/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lolikeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;

import java.util.List;

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
       // keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Key> keys = getKeyboard().getKeys();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(35);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.parseColor("#ff6d6e72"));
        for (Key key : keys) {
            if (key.label != null) {
                if (key.label.equals(";)")) {
                    key.label = new String(Character.toChars(key.codes[0]));
                    /*Log.d("onDraw", "Length= " + key.label.length()
                            + " : CharSeq =" + key.label.toString());*/
                }
               // if (MyConfig.isShowHintLabel()) {
                    if (key.popupCharacters != null) {
                        String popKeyLabel = "";
                        int xPos = key.x + key.width / 1;
                        if (key.popupCharacters.length() > 3) {
                            xPos = key.x + key.width / 2;
                            popKeyLabel = key.popupCharacters.subSequence(0, 2)
                                    .toString();
                        } else {
                            popKeyLabel = key.popupCharacters.toString();
                        }
                        canvas.drawText(popKeyLabel, xPos, key.y + 50, paint);
                    }
               // }
            }
        }
    }
}
