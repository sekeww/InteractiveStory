package com.shapp.www.interactivestory.model;

public class Page {
    private int mImageId;
    private int mTextId;
    private Choice mChoice1;
    private Choice mChoice2;
    private boolean finalPage = false;

    public Page(int imageId, int textId) {
        mImageId = imageId;
        mTextId = textId;
        this.finalPage = true;
    }

    public Page(int imageId, int textId, Choice choice1, Choice choice2) {
        mImageId = imageId;
        mTextId = textId;
        mChoice1 = choice1;
        mChoice2 = choice2;
    }

    public boolean isFinalPage() {
        return finalPage;
    }

    public void setFinalPage(boolean finalPage) {
        this.finalPage = finalPage;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int textId) {
        mTextId = textId;
    }

    public Choice getChoice1() {
        return mChoice1;
    }

    public void setChoice1(Choice choice1) {
        mChoice1 = choice1;
    }

    public Choice getChoice2() {
        return mChoice2;
    }

    public void setChoice2(Choice choice2) {
        mChoice2 = choice2;
    }
}
