package com.nirwashh.astonintensive5;

import android.os.Bundle;
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
        initContacts();
    }

    private void initContacts() {
        contact1 = MyApp.contact1;
        contact2 = MyApp.contact2;
        contact3 = MyApp.contact3;
        contact4 = MyApp.contact4;
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
                        setupUi();
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


    private void updateContacts(Contact contact) {
        if (contact.getId() == 1) {
            contact1.setName(contact.getName());
            contact1.setLastname(contact.getLastname());
            contact1.setTelNumber(contact.getTelNumber());
        } else if (contact.getId() == 2) {
            contact2.setName(contact.getName());
            contact2.setLastname(contact.getLastname());
            contact2.setTelNumber(contact.getTelNumber());
        } else if (contact.getId() == 3) {
            contact3.setName(contact.getName());
            contact3.setLastname(contact.getLastname());
            contact3.setTelNumber(contact.getTelNumber());
        } else if (contact.getId() == 4) {
            contact4.setName(contact.getName());
            contact4.setLastname(contact.getLastname());
            contact4.setTelNumber(contact.getTelNumber());
        } else {
            throw new IllegalStateException("Unexpected value: " + contact.getId());
        }
    }

    private void setupUi() {
        binding.tvNameContact1.setText(contact1.getName());
        binding.tvLastnameContact1.setText(contact1.getLastname());
        binding.tvTelContact1.setText(contact1.getTelNumber());

        binding.tvNameContact2.setText(contact2.getName());
        binding.tvLastnameContact2.setText(contact2.getLastname());
        binding.tvTelContact2.setText(contact2.getTelNumber());

        binding.tvNameContact3.setText(contact3.getName());
        binding.tvLastnameContact3.setText(contact3.getLastname());
        binding.tvTelContact3.setText(contact3.getTelNumber());

        binding.tvNameContact4.setText(contact4.getName());
        binding.tvLastnameContact4.setText(contact4.getLastname());
        binding.tvTelContact4.setText(contact4.getTelNumber());
    }
}

interface ContactsFragmentNavigation {
    void navigateToContactInfoFragment(Contact contact);
}