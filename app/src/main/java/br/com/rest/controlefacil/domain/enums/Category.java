package br.com.rest.controlefacil.domain.enums;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LUCAS RODRIGUES on 29/11/2017.
 */

public enum Category implements Parcelable {
    EXPENSES, RECIPES;

    public static final Parcelable.Creator<Category> CREATOR = new Creator<Category>() {

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }

        @Override
        public Category createFromParcel(Parcel source) {
            return Category.values()[source.readInt()];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ordinal());
    }
}
