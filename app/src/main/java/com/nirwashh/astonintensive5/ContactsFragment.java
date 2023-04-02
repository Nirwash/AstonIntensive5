package com.nirwashh.astonintensive5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.nirwashh.astonintensive5.databinding.FragmentContactsBinding;

public class ContactsFragment extends Fragment {
    private FragmentContactsBinding binding;
    private ContactsFragmentNavigation contactsFragmentNavigation;
    static String TAG = "ContactsFragment";
    private static final String CONTACT = "CONTACT";
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact contact4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsFragmentNavigation = (ContactsFragmentNavigation) requireActivity();
        if (savedInstanceState == null) {
            initContacts();
        } else {
            Log.d("saveInstanceState", savedInstanceState.toString());
            updateContacts(savedInstanceState.getParcelable("contact1"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactsBinding.inflate(inflater, container, false);
        getParentFragmentManager().setFragmentResultListener(
                "requestKey",
                this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Contact contact = result.getParcelable(CONTACT);
                        updateContacts(contact);
                        updateUi(contact);
                    }
                });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi();
        binding.contact1.setOnClickListener(view1 -> {
            contactsFragmentNavigation.navigateToContactInfoFragment(contact1);
        });
        binding.contact2.setOnClickListener(view1 -> {
            contactsFragmentNavigation.navigateToContactInfoFragment(contact2);
        });
        binding.contact3.setOnClickListener(view1 -> {
            contactsFragmentNavigation.navigateToContactInfoFragment(contact3);
        });
        binding.contact4.setOnClickListener(view1 -> {
            contactsFragmentNavigation.navigateToContactInfoFragment(contact4);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("saveInstanceState", contact1.toString());
        outState.putParcelable("contact1", contact1);
    }

    private void initContacts() {
        contact1 = new Contact(
                getString(R.string.contact_name_1),
                getString(R.string.contact_lastname_1),
                getString(R.string.contact_tel_1),
                0
        );
        contact2 = new Contact(
                getString(R.string.contact_name_2),
                getString(R.string.contact_lastname_2),
                getString(R.string.contact_tel_2),
                1
        );
        contact3 = new Contact(
                getString(R.string.contact_name_3),
                getString(R.string.contact_lastname_3),
                getString(R.string.contact_tel_3),
                2
        );
        contact4 = new Contact(
                getString(R.string.contact_name_4),
                getString(R.string.contact_lastname_4),
                getString(R.string.contact_tel_4),
                3
        );
    }

    private void updateContacts(Contact contact) {
        if (contact.id == 0) {
            contact1.name = contact.name;
            contact1.lastname = contact.lastname;
            contact1.telNumber = contact.telNumber;
        } else if (contact.id == 1) {
            contact2.name = contact.name;
            contact2.lastname = contact.lastname;
            contact2.telNumber = contact.telNumber;
        } else if (contact.id == 2) {
            contact3.name = contact.name;
            contact3.lastname = contact.lastname;
            contact3.telNumber = contact.telNumber;
        } else if (contact.id == 3) {
            contact4.name = contact.name;
            contact4.lastname = contact.lastname;
            contact4.telNumber = contact.telNumber;
        } else {
            throw new IllegalStateException("Unexpected value: " + contact.id);
        }
    }

    private void setupUi() {
        binding.tvNameContact1.setText(contact1.name);
        binding.tvLastnameContact1.setText(contact1.lastname);
        binding.tvTelContact1.setText(contact1.telNumber);

        binding.tvNameContact2.setText(contact2.name);
        binding.tvLastnameContact2.setText(contact2.lastname);
        binding.tvTelContact2.setText(contact2.telNumber);

        binding.tvNameContact3.setText(contact3.name);
        binding.tvLastnameContact3.setText(contact3.lastname);
        binding.tvTelContact3.setText(contact3.telNumber);

        binding.tvNameContact4.setText(contact4.name);
        binding.tvLastnameContact4.setText(contact4.lastname);
        binding.tvTelContact4.setText(contact4.telNumber);
    }

    private void updateUi(Contact contact) {
        if (contact.id == 0) {
            binding.tvNameContact1.setText(contact.name);
            binding.tvLastnameContact1.setText(contact1.lastname);
            binding.tvTelContact1.setText(contact1.telNumber);
        } else if (contact.id == 1) {
            binding.tvNameContact2.setText(contact2.name);
            binding.tvLastnameContact2.setText(contact2.lastname);
            binding.tvTelContact2.setText(contact2.telNumber);
        } else if (contact.id == 2) {
            binding.tvNameContact3.setText(contact3.name);
            binding.tvLastnameContact3.setText(contact3.lastname);
            binding.tvTelContact3.setText(contact3.telNumber);
        } else if (contact.id == 3) {
            binding.tvNameContact4.setText(contact4.name);
            binding.tvLastnameContact4.setText(contact4.lastname);
            binding.tvTelContact4.setText(contact4.telNumber);
        } else {
            throw new IllegalStateException("Unexpected value: " + contact.id);
        }
    }
}

interface ContactsFragmentNavigation {
    void navigateToContactInfoFragment(Contact contact);
}