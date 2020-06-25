package com.arhamtechnolabs.karohamapp.adapters;

import com.arhamtechnolabs.karohamapp.ui.DescriptionFragment;
import com.arhamtechnolabs.karohamapp.ui.IncludesFragment;
import com.arhamtechnolabs.karohamapp.ui.InstructorDetailsFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CoursePagerAdapter extends FragmentStatePagerAdapter {


    public CoursePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new IncludesFragment();
            case 1: return new DescriptionFragment();
            case 2: return  new InstructorDetailsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Includes";
            case 1: return "Description";
            case 2: return "Instructor Info";

            default: return null;
        }
    }
}
