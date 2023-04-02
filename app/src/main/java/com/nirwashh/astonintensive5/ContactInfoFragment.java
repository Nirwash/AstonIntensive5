package com.nirwashh.astonintensive5;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nirwashh.astonintensive5.databinding.FragmentContactInfoBinding;

public class ContactInfoFragment extends Fragment {
    public static String TAG = "ContactInfoFragment";
    private static final String CONTACT = "CONTACT";
    private FragmentContactInfoBinding binding;
    private Contact contact;

    public static ContactInfoFragment newInstance(Contact contact) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            contact = args.getParcelable(CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi(contact);

        binding.btnSave.setOnClickListener(view1 -> {
            String name = String.valueOf(binding.tvNameContact.getText());
            String lastname = String.valueOf(binding.tvLastnameContact.getText());
            String telNumber = String.valueOf(binding.tvTelContact.getText());
            Bundle result = new Bundle();
            result.putParcelable(CONTACT, (Parcelable) new Contact(name, lastname, telNumber, contact.id));
            getParentFragmentManager().setFragmentResult("requestKey", result);
            closeFragment();
        });
    }


    private void closeFragment() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void setupUi(Contact contact) {
        binding.tvNameContact.setText(contact.name);
        binding.tvLastnameContact.setText(contact.lastname);
        binding.tvTelContact.setText(contact.telNumber);

    }
}
