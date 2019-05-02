package com.example.TravelCounsellingSystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import questionnaire.Answer;
import questionnaire.Page;
import questionnaire.Question;

public class QuestionnaireActivity extends Activity {
    private LinearLayout test_layout;
    //answer list
    private ArrayList<Answer> the_answer_list;
    //question list
    private ArrayList<Question> the_question_list;
    //the questions in view
    private View que_view;
    //he answers in view
    private View ans_view;
    private LayoutInflater xInflater;
    private Page page;
    //avoid pic changed problem
    //store each imageview
    private ArrayList<ArrayList<ImageView>> imglist=new ArrayList<ArrayList<ImageView>>();
    //存每个答案的imageview
    private ArrayList<ImageView> imglist2;
    public String result;
    public int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        userId= intent.getIntExtra("userId", 0);
        System.out.println("QusetionnaireActivity userIdValue"+userId);
        result = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initDate();
        //submit button
        Button button= findViewById(R.id.submit);
        button.setOnClickListener(new submitOnClickListener(page));
    }
    private void initDate() {
        // TODO Auto-generated method stub
        Answer a_one=new Answer();
        a_one.setAnswerId("0");
        a_one.setAnswer_content("YES");
        a_one.setAns_state(0);
        Answer a_two=new Answer();
        a_two.setAnswerId("1");
        a_two.setAnswer_content("NO");
        a_two.setAns_state(0);

        Answer a_three=new Answer();
        a_three.setAnswerId("2");
        a_three.setAnswer_content("Natural scenery");
        a_three.setAns_state(0);

        Answer a_four=new Answer();
        a_four.setAnswerId("3");
        a_four.setAnswer_content("Human landscape");
        a_four.setAns_state(0);

        Answer a_five=new Answer();
        a_five.setAnswerId("4");
        a_five.setAnswer_content("Entertainment places");
        a_five.setAns_state(0);

        Answer a_six=new Answer();
        a_six.setAnswerId("5");
        a_six.setAnswer_content("New York");
        a_six.setAns_state(0);

        Answer a_seven=new Answer();
        a_seven.setAnswerId("6");
        a_seven.setAnswer_content("Las Vegas");
        a_seven.setAns_state(1);

        Answer a_eight=new Answer();
        a_eight.setAnswerId("7");
        a_eight.setAnswer_content("Barcelona");
        a_eight.setAns_state(0);

        Answer a_nine=new Answer();
        a_nine.setAnswerId("8");
        a_nine.setAnswer_content("Tibet");
        a_nine.setAns_state(1);

        Answer a_ten=new Answer();
        a_ten.setAnswerId("9");
        a_ten.setAnswer_content("The former");
        a_ten.setAns_state(0);

        Answer a_eleven=new Answer();
        a_eleven.setAnswerId("10");
        a_eleven.setAnswer_content("The latter");
        a_eleven.setAns_state(1);

        Answer a_twelve=new Answer();
        a_twelve.setAnswerId("11");
        a_twelve.setAnswer_content("Hawaii");
        a_twelve.setAns_state(1);

        Answer a_thirteen=new Answer();
        a_thirteen.setAnswerId("12");
        a_thirteen.setAnswer_content("Paris");
        a_thirteen.setAns_state(1);

        Answer a_fourteen=new Answer();
        a_fourteen.setAnswerId("13");
        a_fourteen.setAnswer_content("Pompeii");
        a_fourteen.setAns_state(1);

        Answer a_fifteen=new Answer();
        a_fifteen.setAnswerId("14");
        a_fifteen.setAnswer_content("Tokyo");
        a_fifteen.setAns_state(1);


        ArrayList<Answer> answers_one=new ArrayList<Answer>();
        answers_one.add(a_one);
        answers_one.add(a_two);


        ArrayList<Answer> answers_two=new ArrayList<Answer>();
        answers_two.add(a_three);
        answers_two.add(a_four);
        answers_two.add(a_five);

        ArrayList<Answer> answers_three=new ArrayList<Answer>();
        answers_three.add(a_six);
        answers_three.add(a_seven);
        answers_three.add(a_eight);
        answers_three.add(a_nine);

        ArrayList<Answer> answers_four=new ArrayList<Answer>();
        answers_four.add(a_ten);
        answers_four.add(a_eleven);

        ArrayList<Answer> answers_five=new ArrayList<Answer>();
        answers_five.add(a_twelve);
        answers_five.add(a_thirteen);
        answers_five.add(a_fourteen);
        answers_five.add(a_fifteen);





        Question q_one=new Question();
        q_one.setquestionId("00");
        q_one.setType("0");
        q_one.setContent("Do you prefer a tight schedule during travelling?");
        q_one.setAnswers(answers_one);
        q_one.setQue_state(0);

        Question q_two=new Question();
        q_two.setquestionId("01");
        q_two.setType("1");
        q_two.setContent("Which of the following types of city you prefer？");
        q_two.setAnswers(answers_two);
        q_two.setQue_state(0);

        Question q_three=new Question();
        q_three.setquestionId("02");
        q_three.setType("0");
        q_three.setContent("Which of the following cities do you prefer the most?");
        q_three.setAnswers(answers_three);
        q_three.setQue_state(0);

        Question q_four=new Question();
        q_four.setquestionId("03");
        q_four.setType("0");
        q_four.setContent("Do you prefer enjoying local life during your trip or merely visiting tourist attractions in the city?");
        q_four.setAnswers(answers_four);
        q_four.setQue_state(0);

        Question q_five=new Question();
        q_five.setquestionId("04");
        q_five.setType("0");
        q_five.setContent("Which of the following cities do you prefer the most?");
        q_five.setAnswers(answers_five);
        q_five.setQue_state(0);




        ArrayList<Question> questions=new ArrayList<Question>();
        questions.add(q_one);
        questions.add(q_two);
        questions.add(q_three);
        questions.add(q_four);
        questions.add(q_five);


        page=new Page();
        page.setPageId("000");
        page.setStatus("0");
        page.setTitle("User Preference");
        page.setquestions(questions);
        //init view
        initView(page);
    }
    private void initView(Page page) {
        // TODO Auto-generated method stub
        //ADD DYNAMIC CONTENT
        test_layout= findViewById(R.id.lly_test);
        TextView page_txt= findViewById(R.id.txt_title);
        page_txt.setText(page.getTitle());
        //get SECOND VIEW
        the_question_list=page.getquestions();
        //init DYNAMIC CONTENT
        for(int i=0;i<the_question_list.size();i++){
            que_view=xInflater.inflate(R.layout.question_layout, null);
            TextView txt_que= que_view.findViewById(R.id.txt_question_item);
            LinearLayout add_layout= que_view.findViewById(R.id.lly_answer);
            if(the_question_list.get(i).getType().equals("1")){
                set(txt_que,the_question_list.get(i).getContent(),1);
            }else{
                set(txt_que,the_question_list.get(i).getContent(),0);
            }
            the_answer_list=the_question_list.get(i).getAnswers();
            imglist2=new ArrayList<ImageView>();
            for(int j=0;j<the_answer_list.size();j++){
                ans_view=xInflater.inflate(R.layout.answer_layout, null);
                TextView txt_ans= ans_view.findViewById(R.id.txt_answer_item);
                ImageView image= ans_view.findViewById(R.id.image);
                View line_view=ans_view.findViewById(R.id.vw_line);
                if(j==the_answer_list.size()-1){
                    line_view.setVisibility(View.GONE);
                }
                //IF MULTIPLE
                if(the_question_list.get(i).getType().equals("1")){
                    image.setBackgroundDrawable(getResources().getDrawable(R.drawable.ra1));
                }else{
                    image.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio));
                }
                Log.e("---", "------"+image);
                imglist2.add(image);
                txt_ans.setText(the_answer_list.get(j).getAnswer_content());
                LinearLayout lly_answer_size= ans_view.findViewById(R.id.lly_answer_size);
                lly_answer_size.setOnClickListener(new answerItemOnClickListener(i,j,the_answer_list,txt_ans));
                add_layout.addView(ans_view);
            }
            imglist.add(imglist2);

            test_layout.addView(que_view);
        }

    }
    private void set(TextView tv_test, String content,int type) {
        // TODO Auto-generated method stub
        String w;
        if(type==1){
            w = content+"";
        }else{
            w = content+"*";
        }

        int start = content.length();
        int end = w.length();
        Spannable word = new SpannableString(w);
        word.setSpan(new AbsoluteSizeSpan(25), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv_test.setText(word);
    }
    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        private int j;
        private TextView txt;
        private ArrayList<Answer> the_answer_lists;
        public answerItemOnClickListener(int i,int j, ArrayList<Answer> the_answer_list,TextView text){
            this.i=i;
            this.j=j;
            this.the_answer_lists=the_answer_list;
            this.txt=text;

        }
        //after clicked
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            if(the_question_list.get(i).getType().equals("1")){
                //multi
                if(the_answer_lists.get(j).getAns_state()==0){
                    //not clicked
                    txt.setTextColor(Color.parseColor("#EA5514"));
                    imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.ra2));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }else{
                    txt.setTextColor(Color.parseColor("#595757"));
                    imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.ra1));
                    the_answer_lists.get(j).setAns_state(0);
                    the_question_list.get(i).setQue_state(1);
                }
            }else{
                //not multi

                for(int z=0;z<the_answer_lists.size();z++){
                    the_answer_lists.get(z).setAns_state(0);
                    imglist.get(i).get(z).setBackgroundDrawable(getResources().getDrawable(R.drawable.radio));
                }
                if(the_answer_lists.get(j).getAns_state()==0){
                    //not clicked
                    imglist.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.circletick));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }else{
                    //clciked
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }

            }
            //if clicked



        }

    }
    class submitOnClickListener implements View.OnClickListener {
        private Page page;
        public submitOnClickListener(Page page){
            this.page=page;
        }
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //if answered
            boolean isState=true;
            //THE LAST JASON list
            JSONArray jsonArray = new JSONArray();
            //get the situation
            for(int i=0;i<the_question_list.size();i++){
                the_answer_list=the_question_list.get(i).getAnswers();
                //判断是否有题没答完
                if(the_question_list.get(i).getQue_state()==0){
                    Toast.makeText(getApplicationContext(), "Question "+(i+1)+" not finished", Toast.LENGTH_LONG).show();
                    jsonArray=null;
                    isState=false;
                    break;
                }else{
                    for(int j=0;j<the_answer_list.size();j++){
                        if(the_answer_list.get(j).getAns_state()==1){
                            JSONObject json = new JSONObject();
                            try {
                                json.put("psychologicalId", page.getPageId());
                                json.put("questionId", the_question_list.get(i).getquestionId());
                                json.put("optionId", the_answer_list.get(j).getAnswerId());
                                jsonArray.put(json);
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
            if(isState){
                if(jsonArray.length()>0){
                    Log.e("af", jsonArray.toString());
                    for(int item=0;item<jsonArray.length();item++){
                        JSONObject job;
                        try {
                            job = jsonArray.getJSONObject(item);
                            Log.e("----", "pageId--------"+job.get("pageId"));
                            Log.e("----", "questionId--------"+job.get("questionId"));
                            Log.e("----", "answerId--------"+job.get("answerId"));
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }  //traverse the lists

                    }

                }

            }
            for(int i=0; i<2;i++){
                for(int j=0;j<the_question_list.get(i).getAnswers().size();j++){

                    System.out.println("answer"+i+" "+j+" "+the_question_list.get(i).getAnswers().get(j).getAns_state());
                    result = result+the_question_list.get(i).getAnswers().get(j).getAns_state();
                }
            }

            System.out.println("result "+result);
            result = result.replace('0', 'N');
            result = result.replace('1', 'Y');
            if(result.substring(0,1).equals("Y")){
                result = "Y"+result.substring(2,5);
            }
            else{
                result = "N"+result.substring(2,5);
            }
            System.out.println("result "+result);

//            Intent intent1=new Intent("android.intent.action.QUERY_FOLLOW");
//            intent1.putExtra("prefer",result);
            Intent intent=new Intent("android.intent.action.MAINPAGE");
            intent.putExtra("prefer",result);
            intent.putExtra("userId", userId);
            startActivity(intent);

        }
    }



}
