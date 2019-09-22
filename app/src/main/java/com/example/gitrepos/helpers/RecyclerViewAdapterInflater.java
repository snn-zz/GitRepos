package com.example.gitrepos.helpers;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

interface RecyclerViewAdapterInflater {

    View inflateLayout(ViewGroup parent, @LayoutRes int layoutResId);
}

