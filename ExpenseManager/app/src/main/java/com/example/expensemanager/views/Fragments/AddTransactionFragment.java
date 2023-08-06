package com.example.expensemanager.views.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.expensemanager.Adapter.AccountsAdapter;
import com.example.expensemanager.Adapter.CategoryAdapter;
import com.example.expensemanager.Models.Account;
import com.example.expensemanager.Models.Category;
import com.example.expensemanager.Models.Transaction;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.CategoryDailogBinding;
import com.example.expensemanager.databinding.FragmentAddTransactionBinding;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.views.Activities.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddTransactionFragment extends BottomSheetDialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddTransactionBinding binding;
    Transaction transaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddTransactionBinding.inflate(inflater);

        transaction = new Transaction();

        binding.incomeBtn.setOnClickListener(v->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
            transaction.setType(Constants.INCOME);

        });
        binding.expenseBtn.setOnClickListener(v->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.greenColor));
            transaction.setType(Constants.EXPENSE);
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                        calendar.set(Calendar.MONTH,datePicker.getMonth());
                        calendar.set(Calendar.YEAR,datePicker.getYear());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
                        String dateShow = dateFormat.format(calendar.getTime());
                        binding.date.setText(dateShow);

                        transaction.setDate(calendar.getTime());
                        transaction.setId(calendar.getTime().getTime());
                    }
                });
                datePickerDialog.show();
            }
        });

        binding.category.setOnClickListener(v->{
            CategoryDailogBinding categoryDailogBinding = CategoryDailogBinding.inflate(inflater);
            AlertDialog categoryDailog = new AlertDialog.Builder(getContext()).create();
            categoryDailog.setView(categoryDailogBinding.getRoot());

            ArrayList<Category> categories = new ArrayList<>();
            categories.add(new Category("Salary",R.drawable.ic_salary,R.color.category1));
            categories.add(new Category("Business",R.drawable.ic_business,R.color.category2));
            categories.add(new Category("Investment",R.drawable.ic_investment,R.color.category3));
            categories.add(new Category("Loan",R.drawable.ic_loan,R.color.category4));
            categories.add(new Category("Rent",R.drawable.ic_rent,R.color.category5));
            categories.add(new Category("Other",R.drawable.ic_other,R.color.category6));

            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories, new CategoryAdapter.CategoryClickLIstner() {
                @Override
                public void onCategoryClicked(Category category) {
                    binding.category.setText(category.getCategoryName());
                    transaction.setCategory(category.getCategoryName());
                    categoryDailog.dismiss();
                }
            });
            categoryDailogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            categoryDailogBinding.recyclerView.setAdapter(categoryAdapter);
            categoryDailog.show();
        });

        binding.account.setOnClickListener(v->{
            CategoryDailogBinding categoryDailogBinding = CategoryDailogBinding.inflate(inflater);
            AlertDialog accountsDailog = new AlertDialog.Builder(getContext()).create();
            accountsDailog.setView(categoryDailogBinding.getRoot());

            ArrayList<Account> accountArrayList = new ArrayList<>();

            accountArrayList.add(new Account(0,"Cash"));
            accountArrayList.add(new Account(0,"Bank"));
            accountArrayList.add(new Account(0,"Paytm"));
            accountArrayList.add(new Account(0,"other"));

            AccountsAdapter adapter = new AccountsAdapter(getContext(), accountArrayList, new AccountsAdapter.AccountsClickListner() {
                @Override
                public void onAccountSelected(Account account) {
                    binding.account.setText(account.getAccountName());
                    transaction.setAccount(account.getAccountName());
                    accountsDailog.dismiss();
                }
            });
            categoryDailogBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            categoryDailogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            categoryDailogBinding.recyclerView.setAdapter(adapter);
            accountsDailog.show();

        });

        binding.saveTransactionBtn.setOnClickListener(v->{
            double amount  = Double.parseDouble(binding.amount.getText().toString());
            String note = binding.note.getText().toString();

            if(transaction.getType().equals(Constants.EXPENSE)){
                amount = -1*amount;
            }
            transaction.setAmount(amount);
            transaction.setNote(note);

            ((MainActivity)getActivity()).viewModel.addTransactons(transaction);
            ((MainActivity)getActivity()).getTransaction();
            dismiss();
        });
        return binding.getRoot();
    }
}