package com.nirwashh.astonintensive5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nirwashh.astonintensive5.databinding.FragmentContactsBinding;
import com.nirwashh.astonintensive5.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private FragmentContactsBinding binding;
    private ContactsFragmentNavigation contactsFragmentNavigation;
    static String TAG = "ContactsFragment";
    private ContactAdapter contactAdapter;
    ArrayList<Contact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsFragmentNavigation = (ContactsFragmentNavigation) requireActivity();
        contacts = (ArrayList<Contact>) ContactApp.Companion.getContacts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactsBinding.inflate(inflater, container, false);
        initRecycler();
        initSearchView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        OnContactClickListener listener = new OnContactClickListener() {
            @Override
            public void onContactClick(@NonNull Contact contact) {
                contactsFragmentNavigation.navigateToContactInfoFragment(contact);
            }

            @Override
            public void onContactLongClick(int position) {
                contacts.remove(position);
                contactAdapter.removeItem(position);
            }
        };
        contactAdapter = new ContactAdapter(listener);
        contactAdapter.updateList(contacts);
        binding.rcView.setLayoutManager(layoutManager);
        binding.rcView.setAdapter(contactAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL);
        CustomDecoration customDecoration = new CustomDecoration();
        binding.rcView.addItemDecoration(itemDecoration);
        binding.rcView.addItemDecoration(customDecoration);
    }

    private void initSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });
    }

    private void filterList(String query) {
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getLastName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(contact);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "no elements", Toast.LENGTH_SHORT).show();
        } else {
            contactAdapter.updateList(filteredList);
        }
    }
}

interface ContactsFragmentNavigation {
    void navigateToContactInfoFragment(Contact contact);
}