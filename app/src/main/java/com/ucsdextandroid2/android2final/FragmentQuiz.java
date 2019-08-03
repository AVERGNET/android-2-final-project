package com.ucsdextandroid2.android2final;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import org.w3c.dom.Text;

import java.util.List;


public class FragmentQuiz extends Fragment {


    private RadioGroup radioGroup;
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;
    private RadioButton radioButtonD;
    TextView questionView;

    private Question currentQuestion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_quiz, container, false);

    }

    @Nullable
    private Integer validateAnswer() {
        // Validate the current answer selected.
        int selectedButtonId = this.radioGroup.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)getView().findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.getCorrect_answer().equals(answerSelectedStr)) {
               return 1;
            }
            else {
                return 0;
            }
        }
        else {
            // No answer selected.
            Toast.makeText(requireContext(), "Please Select An Answer", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentQuizArgs args = FragmentQuizArgs.fromBundle(getArguments());
        String difficulty = args.getGameDifficulty();
        Integer category = args.getGameCategory();

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioButtonA = (RadioButton )view.findViewById(R.id.ChoiceA);
        radioButtonB = (RadioButton )view.findViewById(R.id.ChoiceB);
        radioButtonC = (RadioButton) view.findViewById(R.id.ChoiceC);
        radioButtonD = (RadioButton) view.findViewById(R.id.ChoiceD);

        questionView = (TextView) view.findViewById(R.id.questionView);


        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer score = validateAnswer();
                if(score != null) {
                    goToResults(category, difficulty, score);
                }
            }
        });

        loadQuesiont(category, difficulty);

    }

    public void setUpQuestionView(Question quesionData){
        this.currentQuestion = quesionData;

        questionView.setText(quesionData.getQuestion());
        radioButtonA.setText(quesionData.getCorrect_answer());
        radioButtonB.setText(quesionData.getIncorrect_answers().get(0));
        radioButtonC.setText(quesionData.getIncorrect_answers().get(1));
        radioButtonD.setText(quesionData.getIncorrect_answers().get(2));

    }


    public void loadQuesiont(Integer category, String difficulty){
        DataSource.getInstance().search(category, difficulty, new DataSource.Callback<List<Question>>() {
            @Override
            public void onDataFetched(List<Question> data) {
                if(data.size() != 0){
                    setUpQuestionView(data.get(0));
                }
            }
        });
    }



    public void goToResults(Integer category, String difficulty,Integer score){
        Navigation.findNavController(getView())
                .navigate(FragmentQuizDirections.actionQuizFragmentToGameResultsFragment(score, difficulty, category));
    }
}
