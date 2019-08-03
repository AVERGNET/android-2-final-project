package com.ucsdextandroid2.android2final;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryFragmentArgs args = CategoryFragmentArgs.fromBundle(getArguments());
        String difficulty = args.getGameDifficulty();

        view.findViewById(R.id.buttonComputers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuiz(18, difficulty);
            }
        });

        view.findViewById(R.id.buttonManga).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuiz(31, difficulty);
            }
        });

        view.findViewById(R.id.buttonGeneral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuiz(9, difficulty);
            }
        });

    }
    public void goToQuiz(Integer category, String difficulty){
        Navigation.findNavController(getView())
                .navigate(CategoryFragmentDirections.actionCategoryFragmentToQuizFragment(difficulty, category));
    }

}
