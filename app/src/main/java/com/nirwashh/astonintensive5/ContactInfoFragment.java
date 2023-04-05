package com.nirwashh.astonintensive5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nirwashh.astonintensive5.databinding.FragmentContactInfoBinding;
import com.nirwashh.astonintensive5.model.Contact;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
            contact.setName(name);
            contact.setLastName(lastname);
            contact.setTelNumber(telNumber);
            Bundle result = new Bundle();
            result.putParcelable(CONTACT, contact);
            getParentFragmentManager().setFragmentResult("requestKey", result);
            closeFragment();
        });
    }


    private void closeFragment() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void setupUi(Contact contact) {
        binding.tvNameContact.setText(contact.getName());
        binding.tvLastnameContact.setText(contact.getLastName());
        binding.tvTelContact.setText(contact.getTelNumber());

    }
}
