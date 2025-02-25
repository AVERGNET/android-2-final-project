package com.ucsdextandroid2.android2final;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

public class DifficultyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_difficulty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonHard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategory("hard");
            }
        });

        view.findViewById(R.id.buttonMedium).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategory("medium");
            }
        });

        view.findViewById(R.id.buttonEasy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategory("easy");
            }
        });
    }

    public void goToCategory(String difficulty){
        Navigation.findNavController(getView())
                .navigate(DifficultyFragmentDirections.actionDifficultyFragmentToCategoryFragment(difficulty));
    }
}
