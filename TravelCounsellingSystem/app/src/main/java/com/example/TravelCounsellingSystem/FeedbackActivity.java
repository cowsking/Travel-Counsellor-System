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

public class FeedbackActivity extends Activity{
    private LinearLayout test_layout1;
    private Page the_page;
    //Answer list
    private ArrayList<Answer> the_answer_list1;
    //Question list
    private ArrayList<Question> the_question_list1;
    //the view that question located in
    private View que_view1;
    //the view that answer located in
    private View ans_view1;
    private LayoutInflater xInflater1;
    private Page page1;
    private String activityName;
    //change the imageview onclick
    //imageview for question
    private ArrayList<ArrayList<ImageView>> imglist01=new ArrayList<ArrayList<ImageView>>();
    //imageview for answer
    private ArrayList<ImageView> imglist02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent lastintent=getIntent();
         activityName=lastintent.getStringExtra("activityname");
        xInflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initDate();
        //submit button
        Button button= findViewById(R.id.submit1);
        TextView textView =findViewById(R.id.opinion);
        button.setOnClickListener(new submitOnClickListener(page1));

    }
    private void initDate() {

        // TODO Auto-generated method stub

        //set choices separately
        Answer a_one=new Answer();
        a_one.setAnswerId("0");
        a_one.setAnswer_content("Yes");
        a_one.setAns_state(0);
        Answer a_two=new Answer();
        a_two.setAnswerId("1");
        a_two.setAnswer_content("No");
        a_two.setAns_state(0);

        Answer a_three=new Answer();
        a_three.setAnswerId("3");
        a_three.setAnswer_content("0-16");
        a_three.setAns_state(0);

        Answer a_four=new Answer();
        a_four.setAnswerId("4");
        a_four.setAnswer_content("16-28");
        a_four.setAns_state(0);



        Answer a_five=new Answer();
        a_five.setAnswerId("5");
        a_five.setAnswer_content("28-40");
        a_five.setAns_state(0);

        Answer a_six=new Answer();
        a_six.setAnswerId("6");
        a_six.setAnswer_content("40-60");
        a_six.setAns_state(0);

        Answer a_seven=new Answer();
        a_seven.setAnswerId("7");
        a_seven.setAnswer_content("60+");
        a_seven.setAns_state(0);

        Answer a_eight=new Answer();
        a_eight.setAnswerId("8");
        a_eight.setAnswer_content("From Google Play Store");
        a_eight.setAns_state(0);

        Answer a_nine=new Answer();
        a_nine.setAnswerId("9");
        a_nine.setAnswer_content("Through ADs");
        a_nine.setAns_state(0);

        Answer a_ten=new Answer();
        a_ten.setAnswerId("10");
        a_ten.setAnswer_content("Through Friends");
        a_ten.setAns_state(0);

        Answer a_eleven=new Answer();
        a_eleven.setAnswerId("11");
        a_eleven.setAnswer_content("Others");
        a_eleven.setAns_state(0);

        Answer a_twelve=new Answer();
        a_twelve.setAnswerId("12");
        a_twelve.setAnswer_content("Strongly disagree");
        a_twelve.setAns_state(0);

        Answer a_thirteen=new Answer();
        a_thirteen.setAnswerId("13");
        a_thirteen.setAnswer_content("Disagree");
        a_thirteen.setAns_state(0);

        Answer a_fourteen=new Answer();
        a_fourteen.setAnswerId("14");
        a_fourteen.setAnswer_content("Agree");
        a_fourteen.setAns_state(0);

        Answer a_fifteen=new Answer();
        a_fifteen.setAnswerId("15");
        a_fifteen.setAnswer_content("Strongly agree");
        a_fifteen.setAns_state(0);

        Answer a_sixteen=new Answer();
        a_sixteen.setAnswerId("16");
        a_sixteen.setAnswer_content("Route management");
        a_sixteen.setAns_state(0);

        Answer a_seventeen=new Answer();
        a_seventeen.setAnswerId("17");
        a_seventeen.setAnswer_content("Transportation information");
        a_seventeen.setAns_state(0);

        Answer a_eighteen=new Answer();
        a_eighteen.setAnswerId("18");
        a_eighteen.setAnswer_content("Hotel information");
        a_eighteen.setAns_state(0);

        Answer a_ninteen=new Answer();
        a_ninteen.setAnswerId("19");
        a_ninteen.setAnswer_content("Forum");
        a_ninteen.setAns_state(0);

        //add the choices of each question into respectively arraylist
        ArrayList<Answer> answers_one=new ArrayList<Answer>();
        answers_one.add(a_one);
        answers_one.add(a_two);


        ArrayList<Answer> answers_two=new ArrayList<Answer>();
        answers_two.add(a_three);
        answers_two.add(a_four);
        answers_two.add(a_five);
        answers_two.add(a_six);
        answers_two.add(a_seven);


        ArrayList<Answer> answers_three=new ArrayList<Answer>();
        answers_three.add(a_eight);
        answers_three.add(a_nine);
        answers_three.add(a_ten);
        answers_three.add(a_eleven);
        ArrayList<Answer> answers_four=new ArrayList<Answer>();
        answers_four.add(a_twelve);
        answers_four.add(a_thirteen);
        answers_four.add(a_fourteen);
        answers_four.add(a_fifteen);
        ArrayList<Answer> answers_five=new ArrayList<Answer>();
        answers_five.add(a_twelve);
        answers_five.add(a_thirteen);
        answers_five.add(a_fourteen);
        answers_five.add(a_fifteen);

        ArrayList<Answer> answers_six=new ArrayList<Answer>();
        answers_six.add(a_sixteen);
        answers_six.add(a_seventeen);
        answers_six.add(a_eighteen);
        answers_six.add(a_ninteen);

        ArrayList<Answer> answers_seven=new ArrayList<Answer>();

        //setup each questions
        Question q_one=new Question();
        q_one.setquestionId("00");
        q_one.setType("0");
        q_one.setContent("Is This your first time using this system or similar travel planning apps");
        q_one.setAnswers(answers_one);
        q_one.setQue_state(0);

        Question q_two=new Question();
        q_two.setquestionId("01");
        q_two.setType("0");
        q_two.setContent("What is your age?");
        q_two.setAnswers(answers_two);
        q_two.setQue_state(0);

        Question q_three=new Question();
        q_three.setquestionId("02");
        q_three.setType("0");
        q_three.setContent("How do you know this app?");
        q_three.setAnswers(answers_three);
        q_three.setQue_state(0);

        Question q_four=new Question();
        q_four.setquestionId("03");
        q_four.setType("0");
        q_four.setContent("Do you think the app run smoothly?");
        q_four.setAnswers(answers_four);
        q_four.setQue_state(0);

        Question q_five=new Question();
        q_five.setquestionId("04");
        q_five.setType("0");
        q_five.setContent("Have you acquired the proper route as you intended?");
        q_five.setAnswers(answers_five);
        q_five.setQue_state(0);

        Question q_six=new Question();
        q_six.setquestionId("05");
        q_six.setType("1"); //(update)
        q_six.setContent("Which function do you like in further updates?");
        q_six.setAnswers(answers_six);
        q_six.setQue_state(0);

        Question q_seven=new Question();
        q_seven.setquestionId("06");
        q_seven.setType("0");
        q_seven.setContent("Do you have any suggestion or bug reported?");
        q_seven.setAnswers(answers_seven);
        q_seven.setQue_state(0);

        ArrayList<Question> questions=new ArrayList<Question>();
        questions.add(q_one);
        questions.add(q_two);
        questions.add(q_three);
        questions.add(q_four);
        questions.add(q_five);
        questions.add(q_six);
        //questions.add(q_seven);

        page1=new Page();
        page1.setPageId("000");
        page1.setStatus("0");
        page1.setTitle("User Preference");
        page1.setquestions(questions);
        //intialise page view
        initView(page1);
    }
    private void initView(Page page) {
        // TODO Auto-generated method stub
        //add layout
        test_layout1= findViewById(R.id.lly_test1);
        TextView page_txt1= findViewById(R.id.txt_title1);
        page_txt1.setText(page.getTitle());
        //get data of the questions
        the_question_list1=page.getquestions();
        //add layout according to number of questions
        for(int i=0;i<the_question_list1.size();i++){
            que_view1=xInflater1.inflate(R.layout.question_layout, null);
            TextView txt_que1= que_view1.findViewById(R.id.txt_question_item);
            //add answer view
            LinearLayout add_layout= que_view1.findViewById(R.id.lly_answer);
            //single choice or multichoide statement
            if(the_question_list1.get(i).getType().equals("1")){
                set(txt_que1,the_question_list1.get(i).getContent(),1);
            }else{
                set(txt_que1,the_question_list1.get(i).getContent(),0);
            }
            //get data of answers
            the_answer_list1=the_question_list1.get(i).getAnswers();
            imglist02=new ArrayList<ImageView>();
            for(int j=0;j<the_answer_list1.size();j++){
                ans_view1=xInflater1.inflate(R.layout.answer_layout, null);
                TextView txt_ans1= ans_view1.findViewById(R.id.txt_answer_item);
                ImageView image1= ans_view1.findViewById(R.id.image);
                View line_view1=ans_view1.findViewById(R.id.vw_line);
                if(j==the_answer_list1.size()-1){
                    //set lineview visibility
                    line_view1.setVisibility(View.GONE);
                }
                //add imageview for statement of single choice or multichoice
                if(the_question_list1.get(i).getType().equals("1")){
                    image1.setBackgroundDrawable(getResources().getDrawable(R.drawable.ra1));
                }else{
                    image1.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio));
                }
                Log.e("---", "------"+image1);
                imglist02.add(image1);
                txt_ans1.setText(the_answer_list1.get(j).getAnswer_content());
                LinearLayout lly_answer_size1= ans_view1.findViewById(R.id.lly_answer_size);
                lly_answer_size1.setOnClickListener(new answerItemOnClickListener(i,j,the_answer_list1,txt_ans1));
                add_layout.addView(ans_view1);
            }
			/*for(int r=0; r<imglist2.size();r++){
				Log.e("---", "imglist2--------"+imglist2.get(r));
			}*/

            imglist01.add(imglist02);

            test_layout1.addView(que_view1);
        }
		/*for(int q=0;q<imglist.size();q++){
			for(int w=0;w<imglist.get(q).size();w++){
				Log.e("---", "altogether------"+imglist.get(q).get(w));
			}
		}*/

    }
    private void set(TextView tv_test, String content,int type) {
        //add * for multichoice
        // TODO Auto-generated method stub
        String w;
        if(type==1){
            w = content+"*[multi choices]";
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
        //click to item to change the state
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            if(the_question_list1.get(i).getType().equals("1")){
                //multi
                if(the_answer_lists.get(j).getAns_state()==0){
                    //unclicked
                    txt.setTextColor(Color.parseColor("#EA5514"));
                    imglist01.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.ra2));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list1.get(i).setQue_state(1);
                }else{
                    txt.setTextColor(Color.parseColor("#595757"));
                    imglist01.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.ra1));
                    the_answer_lists.get(j).setAns_state(0);
                    the_question_list1.get(i).setQue_state(1);
                }
            }else{
                //single

                for(int z=0;z<the_answer_lists.size();z++){
                    the_answer_lists.get(z).setAns_state(0);
                    imglist01.get(i).get(z).setBackgroundDrawable(getResources().getDrawable(R.drawable.radio));
                }
                if(the_answer_lists.get(j).getAns_state()==0){
                    //unclick
                    imglist01.get(i).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.circletick));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list1.get(i).setQue_state(1);
                }else{
                    //clicked
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list1.get(i).setQue_state(1);
                }

            }
            //if clicked



        }

    }
    class submitOnClickListener implements View.OnClickListener {
        private Page page1;

        public submitOnClickListener(Page page){
            this.page1=page;
        }
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //if finished
            boolean isState=true;
            //final JSON array
            JSONArray jsonArray = new JSONArray();
            //validation for question to check if finished
            //question numbers to judge if finish
            for(int i=0;i<the_question_list1.size();i++){
                the_answer_list1=the_question_list1.get(i).getAnswers();
                //if finished
                if(the_question_list1.get(i).getQue_state()==0){
                    Toast.makeText(getApplicationContext(), "您第"+(i+1)+"题没有答完", Toast.LENGTH_LONG).show();
                    jsonArray=null;
                    isState=false;
                    break;
                }else{
                    //upload data
                    for(int j=0;j<the_answer_list1.size();j++){
                        if(the_answer_list1.get(j).getAns_state()==1){
                            JSONObject json = new JSONObject();
                            try {
                                json.put("psychologicalId", page1.getPageId());
                                json.put("questionId", the_question_list1.get(i).getquestionId());
                                json.put("optionId", the_answer_list1.get(j).getAnswerId());
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
                        }  // tranverse the array and change to JSON object

                    }

                }

            }

            Log.e("name",activityName);
            if (activityName.equals("select1")) {
                Intent intent=new Intent("android.intent.action.QUERY_START");
                startActivity(intent);
            }else if(activityName.equals("select2")){
                Intent intent=new Intent("android.intent.action.QUERY_FOLLOW");
                startActivity(intent);
            }else if (activityName.equals("main")){
                Intent intent = new Intent("android.intent.action.MAINPAGE");
                startActivity(intent);
            }
        }
    }

}
