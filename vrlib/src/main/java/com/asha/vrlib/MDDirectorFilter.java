package com.asha.vrlib;

/**
 * Created by hzqiujiadi on 2017/5/13.
 * hzqiujiadi ashqalcn@gmail.com
 */

public class MDDirectorFilter implements MDVRLibrary.IDirectorFilter {

    private MDVRLibrary.IDirectorFilter delegate;

    @Override
    public float onFilterPitch(float input) {
        if (delegate != null){
            return delegate.onFilterPitch(input);
        }

        return input;
    }

    @Override
    public float onFilterYaw(float input) {
        if (delegate != null){
            return delegate.onFilterYaw(input);
        }

        return input;
    }

    @Override
    public float onFilterRoll(float input) {
        if (delegate != null){
            return delegate.onFilterRoll(input);
        }

        return input;
    }

    public void setDelegate(MDVRLibrary.IDirectorFilter delegate) {
        this.delegate = delegate;
    }
}
