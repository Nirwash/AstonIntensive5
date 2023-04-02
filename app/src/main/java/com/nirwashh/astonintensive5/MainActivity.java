package com.nirwashh.astonintensive5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements ContactsFragmentNavigation {
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTablet = getResources().getBoolean(R.bool.is_tablet);
        Fragment fragment = new ContactsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, ContactsFragment.TAG)
                .commit();
    }

    @Override
    public void navigateToContactInfoFragment(Contact contact) {
        Fragment fragment = ContactInfoFragment.newInstance(contact);
        if (isTablet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.second_fragment_container, fragment, ContactInfoFragment.TAG)
                    .addToBackStack(ContactInfoFragment.TAG)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, ContactInfoFragment.TAG)
                    .addToBackStack(ContactInfoFragment.TAG)
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}