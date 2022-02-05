package com.voc.genshin_helper.buff;/*
 * Package com.voc.genshin_helper.buff.CalculatorBuff was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class CalculatorBuff {

    /**
     * Char Transfer
     */
    ArrayList<String> charChoosedNameList = new ArrayList<String>();
    ArrayList<Integer> charChoosedBeforeLvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterLvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeBreakLvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedBeforeBreakUPLvlList= new ArrayList<Boolean>();
    ArrayList<Integer> charChoosedAfterBreakLvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedAfterBreakUPLvlList= new ArrayList<Boolean>();
    ArrayList<Integer> charChoosedBeforeSkill1LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill1LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeSkill2LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill2LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeSkill3LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill3LvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedIsCal= new ArrayList<Boolean>();

    /**
     * Weapon Transfer
     */

    ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedBeforeLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedAfterLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedBeforeBreakLvlList = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedBeforeBreakUPLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedAfterBreakLvlList = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedAfterBreakUPLvlList = new ArrayList<>();
    ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedRare = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedIsCal = new ArrayList<>();
    ArrayList<Integer> weaponChoosedId = new ArrayList<>();

    /**
     * Artifact Transfer
     */

    ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    ArrayList<Integer> artifactChoosedBeforeLvlList = new ArrayList<>();
    ArrayList<Integer> artifactChoosedAfterLvlList = new ArrayList<>();
    ArrayList<String> artifactChoosedFollowList = new ArrayList<>();
    ArrayList<Boolean> artifactChoosedIsCal = new ArrayList<>();
    ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
    ArrayList<String> artifactChoosedType = new ArrayList<>();
    ArrayList<Integer> artifactChoosedId = new ArrayList<>();

    /**
     * Important Method
     */
    Context context;
    View viewPager;
    ItemRss item_rss;
    String dataSheetName;
    SharedPreferences sharedPreferences;
    String color_hex;

    ArrayList<String> artifactBuffMainItem = new ArrayList<>(5);
    ArrayList<Double> artifactBuffMainValue = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec1Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec1Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec2Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec2Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec3Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec3Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec4Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec4Value = new ArrayList<>(5);

    ArrayList<String> artifactBuffSec1ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec1ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec2ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec2ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec3ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec3ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec4ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec4ValueTMP = new ArrayList<>(5);

    int[] artifactBuffSec1Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec2Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec3Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec4Times = new int[]{1,1,1,1,1};


    //============================================================================//

    ArrayList<Double> artifactBasicBaseHP = new ArrayList<>();
    ArrayList<Double> artifactBasicBaseATK = new ArrayList<>();
    ArrayList<Double> artifactBasicHP = new ArrayList<>();
    ArrayList<Double> artifactBasicATK = new ArrayList<>();
    ArrayList<Double> artifactBasicDEF = new ArrayList<>();
    ArrayList<Double> artifactBasicEleMas = new ArrayList<>();
    ArrayList<Double> artifactBasicEnRech = new ArrayList<>();
    ArrayList<Double> artifactBasicPhyDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicEleDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicCritRate = new ArrayList<>();
    ArrayList<Double> artifactBasicCritDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicHealing = new ArrayList<>();

    ArrayList<Double> artifactTierBaseHP = new ArrayList<>();
    ArrayList<Double> artifactTierBaseDEF = new ArrayList<>();
    ArrayList<Double> artifactTierBaseATK = new ArrayList<>();
    ArrayList<Double> artifactTierHP = new ArrayList<>();
    ArrayList<Double> artifactTierDEF = new ArrayList<>();
    ArrayList<Double> artifactTierATK = new ArrayList<>();
    ArrayList<Double> artifactTierEleMas = new ArrayList<>();
    ArrayList<Double> artifactTierEnRech = new ArrayList<>();
    ArrayList<Double> artifactTierCritRate = new ArrayList<>();
    ArrayList<Double> artifactTierCritDMG = new ArrayList<>();

    //============================================================================//

    String[] tmp_artifact_sort ;
    int[] tmp_artifact_lvl ;
    int[] tmp_artifact_rare ;
    int[] tmp_artifact_order ;

    TextView buff_artifact_main_value ;
    TextView buff_artifact_main_name ;
    Spinner buff_artifact_main_name_sp ;
    TextView buff_artifact_sec1_value ;
    TextView buff_artifact_sec1_name ;
    TextView buff_artifact_sec2_value ;
    TextView buff_artifact_sec2_name ;
    TextView buff_artifact_sec3_value ;
    TextView buff_artifact_sec3_name ;
    TextView buff_artifact_sec4_value ;
    TextView buff_artifact_sec4_name ;

    LinearLayout buff_artifact_main ;
    LinearLayout buff_artifact_sec1 ;
    LinearLayout buff_artifact_sec2 ;
    LinearLayout buff_artifact_sec3 ;
    LinearLayout buff_artifact_sec4 ;

    BuffCal2 buffCal;


    public void ui_setup (Context context, View viewPager){
        this.context = context;
        this.viewPager = viewPager;
        item_rss = new ItemRss();
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A");
        readBasicTier();
    }

    public void weapon_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Boolean> arrayList6, ArrayList<Boolean> arrayList7, ArrayList<Boolean> arrayList8, ArrayList<String> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, String arg) {
        this.weaponChoosedNameList = arrayList;
        this.weaponChoosedBeforeLvlList = arrayList2;
        this.weaponChoosedAfterLvlList = arrayList3;
        this.weaponChoosedBeforeBreakLvlList = arrayList4;
        this.weaponChoosedAfterBreakLvlList = arrayList5;
        this.weaponChoosedIsCal = arrayList6;
        this.weaponChoosedBeforeBreakUPLvlList = arrayList7;
        this.weaponChoosedAfterBreakUPLvlList = arrayList8;
        this.weaponChoosedFollowList = arrayList9;
        this.weaponChoosedRare = arrayList10;
        this.weaponChoosedId = arrayList11;
    }

    public void char_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Integer> arrayList6, ArrayList<Integer> arrayList7, ArrayList<Integer> arrayList8, ArrayList<Integer> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, ArrayList<Boolean> arrayList12, ArrayList<Boolean> arrayList13, ArrayList<Boolean> arrayList14,String arg) {
        this.charChoosedNameList = arrayList;
        this.charChoosedBeforeLvlList = arrayList2;
        this.charChoosedAfterLvlList = arrayList3;
        this.charChoosedBeforeBreakLvlList = arrayList4;
        this.charChoosedAfterBreakLvlList = arrayList5;
        this.charChoosedBeforeSkill1LvlList = arrayList6;
        this.charChoosedAfterSkill1LvlList = arrayList7;
        this.charChoosedBeforeSkill2LvlList = arrayList8;
        this.charChoosedAfterSkill2LvlList = arrayList9;
        this.charChoosedBeforeSkill3LvlList = arrayList10;
        this.charChoosedAfterSkill3LvlList = arrayList11;
        this.charChoosedIsCal = arrayList12;
        this.charChoosedBeforeBreakUPLvlList = arrayList13;
        this.charChoosedAfterBreakUPLvlList = arrayList14;
    }

    public void artifact_setup(ArrayList<String> artifactChoosedNameList, ArrayList<Integer> artifactChoosedBeforeLvlList, ArrayList<Integer> artifactChoosedAfterLvlList, ArrayList<Boolean> artifactChoosedIsCal, ArrayList<String> artifactChoosedFollowList, ArrayList<Integer> artifactChoosedRare,ArrayList<String> artifactChoosedType,ArrayList<Integer> artifactChoosedId, String arg) {
        this.artifactChoosedNameList = artifactChoosedNameList;
        this.artifactChoosedBeforeLvlList = artifactChoosedBeforeLvlList;
        this.artifactChoosedAfterLvlList = artifactChoosedAfterLvlList;
        this.artifactChoosedIsCal = artifactChoosedIsCal;
        this.artifactChoosedFollowList = artifactChoosedFollowList;
        this.artifactChoosedRare = artifactChoosedRare;
        this.artifactChoosedType = artifactChoosedType;
        this.artifactChoosedId = artifactChoosedId;
    }

    public void view_setup(){
        /** INIT of UI GONE*/
        GridLayout buff_ui_view = viewPager.findViewById(R.id.buff_ui_view);
        buff_ui_view.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        buff_ui_view.setColumnCount(1);
        buff_ui_view.removeAllViewsInLayout();
        for (int x = 0 ; x < charChoosedNameList.size() ; x++) {

            View view = View.inflate(context, R.layout.item_buff_card, null);

            ImageView buff_ui_char_icon = view.findViewById(R.id.buff_ui_char_icon);
            TextView buff_ui_char_name = view.findViewById(R.id.buff_ui_char_name);
            ImageView buff_ui_weapon_icon = view.findViewById(R.id.buff_ui_weapon_icon);
            TextView buff_ui_press_more = view.findViewById(R.id.buff_ui_press_more);

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);


            if(charChoosedIsCal.get(x) == true) {
                /** Char */
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getCharByName(charChoosedNameList.get(x),context)[3],context))
                        .transform(transformation)
                        .resize(128, 128)
                        .error(R.drawable.paimon_lost)
                        .into(buff_ui_char_icon);
                buff_ui_char_name.setText(item_rss.getCharByName(charChoosedNameList.get(x),context)[1]);

                /** Weapon */
                String tmp_weapon_name = "unknown";
                for (int y = 0 ; y < weaponChoosedNameList.size() ; y ++){
                    if(weaponChoosedFollowList.get(y).equals(charChoosedNameList.get(x))){
                        tmp_weapon_name = weaponChoosedNameList.get(y);
                    }
                }

                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getWeaponByName(tmp_weapon_name,context)[1],context))
                        .transform(transformation)
                        .resize(96, 96)
                        .error(R.drawable.paimon_lost)
                        .into(buff_ui_weapon_icon);

                /** Artifact */

                ArrayList<String> tmp_artifact_name = new ArrayList<String>();
                ArrayList<String> tmp_artifact_type = new ArrayList<String>();
                ArrayList<Integer> tmp_artifact_lvl = new ArrayList<Integer>();
                ArrayList<Integer> tmp_artifact_rare = new ArrayList<Integer>();
                int[] tmp_artifact_id = new int[]{R.id.buff_ui_artifact_icon1,R.id.buff_ui_artifact_icon2,R.id.buff_ui_artifact_icon3,R.id.buff_ui_artifact_icon4,R.id.buff_ui_artifact_icon5};

                for (int y = 0 ; y < artifactChoosedNameList.size() ; y ++){
                    if(item_rss.getCharNameByTranslatedName(artifactChoosedFollowList.get(y),context).equals(charChoosedNameList.get(x)) && !tmp_artifact_type.contains(artifactChoosedType.get(y))){
                        tmp_artifact_name.add(artifactChoosedNameList.get(y));
                        tmp_artifact_type.add(artifactChoosedType.get(y));
                        tmp_artifact_lvl.add(artifactChoosedAfterLvlList.get(y));
                        tmp_artifact_rare.add(artifactChoosedRare.get(y));
                    }
                }

                for (int y = 0 ; y < tmp_artifact_name.size() && y < 5 ; y ++){
                    ImageView img = view.findViewById(tmp_artifact_id[y]);
                    int tmp_artifact_type_id = 1;
                    if(tmp_artifact_type.get(y).equals("Flower")){
                        tmp_artifact_type_id = 4;
                    }else if(tmp_artifact_type.get(y).equals("Plume")){
                        tmp_artifact_type_id = 2;
                    }else if(tmp_artifact_type.get(y).equals("Sand")){
                        tmp_artifact_type_id = 5;
                    }else if(tmp_artifact_type.get(y).equals("Goblet")){
                        tmp_artifact_type_id = 1;
                    }else if(tmp_artifact_type.get(y).equals("Circlet")){
                        tmp_artifact_type_id = 3;
                    }

                    Log.wtf("tmp_artifact_name",tmp_artifact_name.get(y));
                    Log.wtf("tmp_artifact_type_id", String.valueOf(tmp_artifact_type_id));

                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_name.get(y)),context)[tmp_artifact_type_id],context))
                            .transform(transformation)
                            .resize(64, 64)
                            .error(R.drawable.paimon_lost)
                            .into(img);
                }

                int finalX = x;
                String finalTmp_weapon_name = tmp_weapon_name;
                buff_ui_press_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.wtf("TT","OK");
                        buffPage(finalX, finalTmp_weapon_name,tmp_artifact_name,tmp_artifact_type,tmp_artifact_lvl,tmp_artifact_rare);
                    }
                });

            }

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.MATCH_PARENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            view.setLayoutParams (param);
            buff_ui_view.addView(view);
        }

    }

    public void buffPage(int k, String weapon_name, ArrayList<String> artifact_name, ArrayList<String> artifact_type, ArrayList<Integer> artifact_lvl, ArrayList<Integer> artifact_rare){
        /** init */

        buffCal = new BuffCal2();

        artifactBuffMainItem.clear();
        artifactBuffMainValue.clear();
        artifactBuffSec1Item.clear();
        artifactBuffSec1Value.clear();
        artifactBuffSec2Item.clear();
        artifactBuffSec2Value.clear();
        artifactBuffSec3Item.clear();
        artifactBuffSec3Value.clear();
        artifactBuffSec4Item.clear();
        artifactBuffSec4Value.clear();

        artifactBuffSec1ItemTMP.clear();
        artifactBuffSec1ValueTMP.clear();
        artifactBuffSec2ItemTMP.clear();
        artifactBuffSec2ValueTMP.clear();
        artifactBuffSec3ItemTMP.clear();
        artifactBuffSec3ValueTMP.clear();
        artifactBuffSec4ItemTMP.clear();
        artifactBuffSec4ValueTMP.clear();

        for (int  i = 0 ; i < 5 ; i++){
            artifactBuffMainItem.add(i,"");
            artifactBuffSec1Item.add(i,"");
            artifactBuffSec2Item.add(i,"");
            artifactBuffSec3Item.add(i,"");
            artifactBuffSec4Item.add(i,"");

            artifactBuffMainValue.add(i,0.0);
            artifactBuffSec1Value.add(i,0.0);
            artifactBuffSec2Value.add(i,0.0);
            artifactBuffSec3Value.add(i,0.0);
            artifactBuffSec4Value.add(i,0.0);

            artifactBuffSec1ItemTMP.add(i,"");
            artifactBuffSec2ItemTMP.add(i,"");
            artifactBuffSec3ItemTMP.add(i,"");
            artifactBuffSec4ItemTMP.add(i,"");

            artifactBuffSec1ValueTMP.add(i,0.0);
            artifactBuffSec2ValueTMP.add(i,0.0);
            artifactBuffSec3ValueTMP.add(i,0.0);
            artifactBuffSec4ValueTMP.add(i,0.0);
        }

        /**
         * READ THE DATABASE
         */

        /**
         *
         */

        final int radius = 25;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        /** Method */

        String charName = charChoosedNameList.get(k);

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_buff_page, null);

        BackgroundReload.BackgroundReload(context,view);
        // Char_Info
        ImageView buff_char_icon = view.findViewById(R.id.buff_char_icon);
        ImageView buff_char_element = view.findViewById(R.id.buff_char_element);
        TextView buff_char_name = view.findViewById(R.id.buff_char_name);
        TextView buff_char_lvl = view.findViewById(R.id.buff_char_lvl);

        // Char_BUFF_Part
        TextView buff_char_atk_value = view.findViewById(R.id.buff_char_atk_value);
        TextView buff_char_hp_value = view.findViewById(R.id.buff_char_hp_value);
        TextView buff_char_def_value = view.findViewById(R.id.buff_char_def_value);
        TextView buff_char_crit_rate_value = view.findViewById(R.id.buff_char_crit_rate_value);
        TextView buff_char_crit_dmg_value = view.findViewById(R.id.buff_char_crit_dmg_value);
        TextView buff_char_enrech_value = view.findViewById(R.id.buff_char_enrech_value);

        ConstraintLayout buff_char_other_view = view.findViewById(R.id.buff_char_other_view);
        TextView buff_char_other_value = view.findViewById(R.id.buff_char_other_value);
        TextView buff_char_other_name = view.findViewById(R.id.buff_char_other_name);

        // Weapon_Info
        ImageView buff_weapon_icon = view.findViewById(R.id.buff_weapon_icon);
        TextView buff_weapon_name = view.findViewById(R.id.buff_weapon_name);
        TextView buff_weapon_lvl = view.findViewById(R.id.buff_weapon_lvl);

        // Artifact_Icon
        TabLayout buff_artifact_tablayout = view.findViewById(R.id.buff_artifact_tablayout);
        TabItem buff_artifact_tab_flower = view.findViewById(R.id.buff_artifact_tab_flower);
        TabItem buff_artifact_tab_plume = view.findViewById(R.id.buff_artifact_tab_plume);
        TabItem buff_artifact_tab_sand = view.findViewById(R.id.buff_artifact_tab_sand);
        TabItem buff_artifact_tab_goblet = view.findViewById(R.id.buff_artifact_tab_goblet);
        TabItem buff_artifact_tab_circlet = view.findViewById(R.id.buff_artifact_tab_circlet);

        TabLayout buff_buff_tablayout = view.findViewById(R.id.buff_buff_tablayout);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        buff_buff_tablayout.setTabTextColors(context.getResources().getColor(R.color.tv_color), Color.parseColor(color_hex));

        // Artifact_BUFF
        buff_artifact_main_value = view.findViewById(R.id.buff_artifact_main_value);
        buff_artifact_main_name = view.findViewById(R.id.buff_artifact_main_name);
        buff_artifact_main_name_sp = view.findViewById(R.id.buff_artifact_main_name_sp);
        buff_artifact_sec1_value = view.findViewById(R.id.buff_artifact_sec1_value);
        buff_artifact_sec1_name = view.findViewById(R.id.buff_artifact_sec1_name);
        buff_artifact_sec2_value = view.findViewById(R.id.buff_artifact_sec2_value);
        buff_artifact_sec2_name = view.findViewById(R.id.buff_artifact_sec2_name);
        buff_artifact_sec3_value = view.findViewById(R.id.buff_artifact_sec3_value);
        buff_artifact_sec3_name = view.findViewById(R.id.buff_artifact_sec3_name);
        buff_artifact_sec4_value = view.findViewById(R.id.buff_artifact_sec4_value);
        buff_artifact_sec4_name = view.findViewById(R.id.buff_artifact_sec4_name);

        buff_artifact_main = view.findViewById(R.id.buff_artifact_main);
        buff_artifact_sec1 = view.findViewById(R.id.buff_artifact_sec1);
        buff_artifact_sec2 = view.findViewById(R.id.buff_artifact_sec2);
        buff_artifact_sec3 = view.findViewById(R.id.buff_artifact_sec3);
        buff_artifact_sec4 = view.findViewById(R.id.buff_artifact_sec4);

        // Final Display
        TabItem buff_tab_element_reaction = view.findViewById(R.id.buff_tab_element_reaction);
        TabItem buff_tab_normal = view.findViewById(R.id.buff_tab_normal);
        TabItem buff_tab_charged_atk = view.findViewById(R.id.buff_tab_charged_atk);
        TabItem buff_tab_plunging_atk = view.findViewById(R.id.buff_tab_plunging_atk);
        TabItem buff_tab_element = view.findViewById(R.id.buff_tab_element);
        TabItem buff_tab_final = view.findViewById(R.id.buff_tab_final);
        TabItem buff_tab_other = view.findViewById(R.id.buff_tab_other);
        LinearLayout buff_tab_view = view.findViewById(R.id.buff_tab_view);




        /** CHAR_INFO_SET_DATA*/
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(charName,context)[3],context ))
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_full)
                .into (buff_char_icon);

        String name ,element = "Anemo";

        String json_base = LoadData("db/char/char_list.json");
        //Get data from JSON

        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                if(name.equals(charName)){
                    element = object.getString("element");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (element) {
            case "Anemo": buff_char_element.setImageResource(R.drawable.anemo);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_bg);break;
            case "Cryo": buff_char_element.setImageResource(R.drawable.cryo);buff_char_icon.setBackgroundResource(R.drawable.bg_cryo_bg);break;
            case "Electro": buff_char_element.setImageResource(R.drawable.electro);buff_char_icon.setBackgroundResource(R.drawable.bg_electro_bg);break;
            case "Geo": buff_char_element.setImageResource(R.drawable.geo);buff_char_icon.setBackgroundResource(R.drawable.bg_geo_bg);break;
            case "Hydro": buff_char_element.setImageResource(R.drawable.hydro);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_char);break;
            case "Pyro": buff_char_element.setImageResource(R.drawable.pyro);buff_char_icon.setBackgroundResource(R.drawable.bg_pyro_bg);break;
            case "Dendro": buff_char_element.setImageResource(R.drawable.dendro);buff_char_icon.setBackgroundResource(R.drawable.bg_dendro_bg);break;
        }

        buff_char_name.setText(item_rss.getCharByName(charName,context)[1]);
        buff_char_lvl.setText(context.getString(R.string.curr_lvl)+" "+String.valueOf(charChoosedAfterLvlList.get(k)));


        /** WEAPON_INFO_SET_DATA*/

        Log.wtf("HE::","XXX"+weapon_name);

        if(!weapon_name.equals("unknown")) {

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getWeaponByName(weapon_name,context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error(R.drawable.paimon_full)
                    .into(buff_weapon_icon);

            buff_weapon_name.setText(item_rss.getWeaponByName(weapon_name,context)[0]);
            buff_weapon_lvl.setText(context.getString(R.string.aim_lvl) + " " + weaponChoosedAfterLvlList.get(k));
        }

        /** ARTIFACT_ICON_SET_DATA*/

        // 0     , 1    , 2   , 3     , 4
        // Flower, Plume, Sand, Goblet, Circlet
        tmp_artifact_sort = new String[5];
        tmp_artifact_lvl = new int[5];
        tmp_artifact_order = new int[] {4,2,5,1,3};
        tmp_artifact_rare = new int[5];

        for (int x =0 ; x < artifact_type.size() ; x ++){
            switch (artifact_type.get(x)) {
                case "Flower" : tmp_artifact_sort[0] = artifact_name.get(x); tmp_artifact_lvl[0] = artifact_lvl.get(x); tmp_artifact_rare[0] = artifact_rare.get(x); break;
                case "Plume" : tmp_artifact_sort[1] = artifact_name.get(x); tmp_artifact_lvl[1] = artifact_lvl.get(x); tmp_artifact_rare[1] = artifact_rare.get(x); break;
                case "Sand" : tmp_artifact_sort[2] = artifact_name.get(x); tmp_artifact_lvl[2] = artifact_lvl.get(x); tmp_artifact_rare[2] = artifact_rare.get(x); break;
                case "Goblet" : tmp_artifact_sort[3] = artifact_name.get(x); tmp_artifact_lvl[3] = artifact_lvl.get(x); tmp_artifact_rare[3] = artifact_rare.get(x); break;
                case "Circlet" : tmp_artifact_sort[4] = artifact_name.get(x); tmp_artifact_lvl[4] = artifact_lvl.get(x); tmp_artifact_rare[4] = artifact_rare.get(x); break;
            }

        }
        for (int x = 0 ; x < 5 ; x ++) {
            if (tmp_artifact_sort[x] != null) {
                buff_artifact_tablayout.getTabAt(x).setIcon(FileLoader.loadIMG2Drawable(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_sort[x]),context)[tmp_artifact_order[x]],context));
                buff_artifact_tablayout.getTabAt(x).setId(100+x);
            }
        }

        for (int x = 0 ; x < 5 ; x++){
            Log.wtf("HOLY"+String.valueOf(x), String.valueOf(buff_artifact_tablayout.getTabAt(x).getId()));
        }

        buff_artifact_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getId()){
                    case 100 : ArtifactBuffView("Flower");break;
                    case 101 : ArtifactBuffView("Plume");break;
                    case 102 : ArtifactBuffView("Sand");break;
                    case 103 : ArtifactBuffView("Goblet");break;
                    case 104 : ArtifactBuffView("Circlet");break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        /**
         * Calculate Part -> new class
         */

        buffCal.setup(context);
        buffCal.char_setup(charName,charChoosedAfterLvlList.get(k),charChoosedAfterBreakLvlList.get(k),charChoosedAfterBreakUPLvlList.get(k),charChoosedAfterSkill1LvlList.get(k),charChoosedAfterSkill2LvlList.get(k),charChoosedAfterSkill3LvlList.get(k));
        buffCal.weapon_setup(weaponChoosedNameList.get(k),weaponChoosedAfterLvlList.get(k),weaponChoosedAfterBreakLvlList.get(k),weaponChoosedAfterBreakUPLvlList.get(k));
        buffCal.artifact_setup(tmp_artifact_sort,tmp_artifact_lvl,artifactBuffMainValue,artifactBuffMainItem,artifactBuffSec1Value,artifactBuffSec1Item,artifactBuffSec2Value,artifactBuffSec2Item,artifactBuffSec3Value,artifactBuffSec3Item,artifactBuffSec4Value,artifactBuffSec4Item);
        buffCal.startReading();

        /**
         * Feedback -> UI Setting
         */

        buff_char_atk_value.setText(prettyCount(buffCal.atkReturn(),0));
        buff_char_hp_value.setText(prettyCount(buffCal.hpReturn(),0));
        buff_char_def_value.setText(prettyCount(buffCal.defReturn(),0));

        System.out.println("CritTXT : "+prettyCount(buffCal.critRateReturn(),1));
        System.out.println("CritNum : "+buffCal.critRateReturn());

        buff_char_crit_rate_value.setText(prettyCount(buffCal.critRateReturn(),1));
        buff_char_crit_dmg_value.setText(prettyCount(buffCal.critDMGReturn(),1));
        buff_char_enrech_value.setText(prettyCount(buffCal.enRechReturn(),1));

        if(Double.parseDouble(buffCal.otherReturn()[1])>0){
            buff_char_other_view.setVisibility(View.VISIBLE);
            buff_char_other_name.setText(buffCal.otherReturn()[0]);
            buff_char_other_value.setText(prettyCount(Double.parseDouble(buffCal.otherReturn()[1]),1));
        }

        buff_buff_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : buff_tab_view.removeAllViews(); buff_normal(buff_tab_view,buffCal); break;
                    case 1 : buff_tab_view.removeAllViews(); buff_charged(buff_tab_view,buffCal); break;
                    case 2 : buff_tab_view.removeAllViews(); buff_pluging(buff_tab_view,buffCal); break;
                    case 3 : buff_tab_view.removeAllViews(); break;
                    case 4 : buff_tab_view.removeAllViews(); break;
                    case 5 : buff_tab_view.removeAllViews(); break;
                    case 6 : buff_tab_view.removeAllViews(); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : buff_tab_view.removeAllViews(); buff_normal(buff_tab_view,buffCal); break;
                    case 1 : buff_tab_view.removeAllViews(); buff_charged(buff_tab_view,buffCal); break;
                    case 2 : buff_tab_view.removeAllViews(); buff_pluging(buff_tab_view,buffCal); break;
                    case 3 : buff_tab_view.removeAllViews(); break;
                    case 4 : buff_tab_view.removeAllViews(); break;
                    case 5 : buff_tab_view.removeAllViews(); break;
                    case 6 : buff_tab_view.removeAllViews(); break;
                }
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /*
    Sub UI
     */

    public void buff_normal(LinearLayout buff_tab_view, BuffCal2 buffCal){
        buff_tab_view.removeAllViews();

        String[] item_name_recongize = new String[]{"一段傷害","二段傷害","三段傷害","四段傷害","五段傷害","六段傷害","瞄準射擊"};

        String[] item_name = new String[]{"一段傷害","二段傷害","三段傷害","四段傷害","五段傷害","六段傷害","瞄準射擊"};

        for (int i = 0 ; i < item_name_recongize.length ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i])*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i]),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                //buff_uncrit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])));
                buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"PHY"),0));
                buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                //buff_crit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])*buffCal.critDMGReturn()));
                buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"PHY"),0));
                buff_crit_value.setTextColor(context.getColor(R.color.crit));

                buff_tab_view.addView(view);
            }
        }

        /*
        switch (element) {
                    case "Anemo": buff_item_value.setTextColor(context.getColor(R.color.anemo));break;
                    case "Cryo": buff_item_value.setTextColor(context.getColor(R.color.cryo));break;
                    case "Electro": buff_item_value.setTextColor(context.getColor(R.color.electro));break;
                    case "Geo": buff_item_value.setTextColor(context.getColor(R.color.geo));break;
                    case "Hydro": buff_item_value.setTextColor(context.getColor(R.color.hydro));break;
                    case "Pyro": buff_item_value.setTextColor(context.getColor(R.color.pyro));break;
                    case "Dendro": buff_item_value.setTextColor(context.getColor(R.color.dendor));break;
                }
         */
    }

    public void buff_charged(LinearLayout buff_tab_view, BuffCal2 buffCal){
        buff_tab_view.removeAllViews();

        String[] item_name_recongize = new String[]{"滿蓄力瞄準射擊","重擊傷害","重擊循環傷害","重擊終結傷害"};

        String[] item_name = new String[]{"滿蓄力瞄準射擊","重擊傷害","重擊循環傷害","重擊終結傷害"};

        for (int i = 0 ; i < item_name_recongize.length; i++){
            if(buffCal.skillPReturn(item_name_recongize[i])*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i]),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"PHY"),0));
                buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"PHY"),0));
                buff_crit_value.setTextColor(context.getColor(R.color.crit));

                buff_tab_view.addView(view);
            }
        }
    }

    public void buff_pluging(LinearLayout buff_tab_view, BuffCal2 buffCal){
        buff_tab_view.removeAllViews();

        String[] item_name_recongize = new String[]{"下墜期間傷害","低空墜地衝擊傷害","高空墜地衝擊傷害"};

        String[] item_name = new String[]{"下墜期間傷害","低空墜地衝擊傷害","高空墜地衝擊傷害"};

        for (int i = 0 ; i < item_name_recongize.length ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i])*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i]),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"PHY"),0));
                buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"PHY"),0));
                buff_crit_value.setTextColor(context.getColor(R.color.crit));

                buff_tab_view.addView(view);
            }
        }
    }


    public void secSetShow(int sec_id, int i,int pos_id,TextView valueTV, RadioButton buff_artifact_1t, RadioButton buff_artifact_2t, RadioButton buff_artifact_3t, RadioButton buff_artifact_4t, RadioButton buff_artifact_1u, RadioButton buff_artifact_2u, RadioButton buff_artifact_3u, RadioButton buff_artifact_4u, RadioButton buff_artifact_5u){

        double[][] rare5SecBuffData = new double[][]{
                {0.041,0.041,0.051,14,209,16,16,0.045,0.027,0.054},
                {0.047,0.047,0.058,16,239,19,19,0.052,0.031,0.062},
                {0.053,0.053,0.066,18,269,21,21,0.058,0.035,0.070},
                {0.058,0.058,0.073,19,299,23,23,0.065,0.039,0.078}
        };

        String[] artifactSecBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_DEF),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};


        int finalTmp_grade = 0;
        int finalTmp_upgrade = 0;

        if(buff_artifact_1t.isChecked()){
            finalTmp_grade = 0;
        }else if(buff_artifact_2t.isChecked()){
            finalTmp_grade = 1;
        }else if(buff_artifact_3t.isChecked()){
            finalTmp_grade = 2;
        }else if(buff_artifact_4t.isChecked()){
            finalTmp_grade = 3;
        }

        if(buff_artifact_1u.isChecked()){
            finalTmp_upgrade = 1;
        }else if(buff_artifact_2u.isChecked()){
            finalTmp_upgrade = 2;
        }else if(buff_artifact_3u.isChecked()){
            finalTmp_upgrade = 3;
        }else if(buff_artifact_4u.isChecked()){
            finalTmp_upgrade = 4;
        }else if(buff_artifact_5u.isChecked()){
            finalTmp_upgrade = 5;
        }

        pos_id = pos_id -1;

        switch (sec_id){
            case 1 : {
                buff_artifact_sec1_name.setText(artifactSecBuffList[i]);
                if(i !=3 && i != 4 && i != 5 && i != 6){
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,1));
                }else{
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,0));
                }

                artifactBuffSec1ItemTMP.set(pos_id,artifactSecBuffList[i]);
                artifactBuffSec1ValueTMP.set(pos_id,rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade);
                break;
            }
            case 2 : {
                buff_artifact_sec2_name.setText(artifactSecBuffList[i]);
                if(i !=3 && i != 4 && i != 5 && i != 6){
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,1));
                }else{
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,0));
                }

                artifactBuffSec2ItemTMP.set(pos_id,artifactSecBuffList[i]);
                artifactBuffSec2ValueTMP.set(pos_id,rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade);
                break;
            }
            case 3 : {
                buff_artifact_sec3_name.setText(artifactSecBuffList[i]);
                if(i !=3 && i != 4 && i != 5 && i != 6){
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,1));
                }else{
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,0));
                }

                artifactBuffSec3ItemTMP.set(pos_id,artifactSecBuffList[i]);
                artifactBuffSec3ValueTMP.set(pos_id,rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade);
                break;
            }
            case 4 : {
                buff_artifact_sec4_name.setText(artifactSecBuffList[i]);
                if(i !=3 && i != 4 && i != 5 && i != 6){
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,1));
                }else{
                    valueTV.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,0));
                }

                artifactBuffSec4ItemTMP.set(pos_id,artifactSecBuffList[i]);
                artifactBuffSec4ValueTMP.set(pos_id,rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade);
                break;
            }
        }
    }

    public void showResult(int tmp_id){

        artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
        buff_artifact_sec1_name.setText(artifactBuffSec1Item.get(tmp_id));
        if(!artifactBuffSec1Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk)) && !artifactBuffSec1Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP)) && !artifactBuffSec1Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF)) && !artifactBuffSec1Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))){
            buff_artifact_sec1_value.setText(prettyCount(artifactBuffSec1Value.get(tmp_id),1));
        }else{
            buff_artifact_sec1_value.setText(prettyCount(artifactBuffSec1Value.get(tmp_id),0));
        }

        buff_artifact_sec2_name.setText(artifactBuffSec2Item.get(tmp_id));
        if(!artifactBuffSec2Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk)) && !artifactBuffSec2Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP)) && !artifactBuffSec2Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF)) && !artifactBuffSec2Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))){
            buff_artifact_sec2_value.setText(prettyCount(artifactBuffSec2Value.get(tmp_id),1));
        }else{
            buff_artifact_sec2_value.setText(prettyCount(artifactBuffSec2Value.get(tmp_id),0));
        }

        buff_artifact_sec3_name.setText(artifactBuffSec3Item.get(tmp_id));
        if(!artifactBuffSec3Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk)) && !artifactBuffSec3Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP)) && !artifactBuffSec3Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF)) && !artifactBuffSec3Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))){
            buff_artifact_sec3_value.setText(prettyCount(artifactBuffSec3Value.get(tmp_id),1));
        }else{
            buff_artifact_sec3_value.setText(prettyCount(artifactBuffSec3Value.get(tmp_id),0));
        }

        buff_artifact_sec4_name.setText(artifactBuffSec4Item.get(tmp_id));
        if(!artifactBuffSec4Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk)) && !artifactBuffSec4Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP)) && !artifactBuffSec4Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF)) && !artifactBuffSec4Item.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))){
            buff_artifact_sec4_value.setText(prettyCount(artifactBuffSec4Value.get(tmp_id),1));
        }else{
            buff_artifact_sec4_value.setText(prettyCount(artifactBuffSec4Value.get(tmp_id),0));
        }
    }

    public void BuffDialogDisplay(int pos_id,int sec_id, int tmp_id){
        String[] artifactSecBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_DEF),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.menu_buff_artifact, null);

        Spinner sec_sp = view.findViewById(R.id.buff_sec_name);
        TextView valueTV = view.findViewById(R.id.buff_sec_num);

        RadioButton buff_artifact_1t = view.findViewById(R.id.buff_artifact_1t);
        RadioButton buff_artifact_2t = view.findViewById(R.id.buff_artifact_2t);
        RadioButton buff_artifact_3t = view.findViewById(R.id.buff_artifact_3t);
        RadioButton buff_artifact_4t = view.findViewById(R.id.buff_artifact_4t);

        RadioButton buff_artifact_1u = view.findViewById(R.id.buff_artifact_1u);
        RadioButton buff_artifact_2u = view.findViewById(R.id.buff_artifact_2u);
        RadioButton buff_artifact_3u = view.findViewById(R.id.buff_artifact_3u);
        RadioButton buff_artifact_4u = view.findViewById(R.id.buff_artifact_4u);
        RadioButton buff_artifact_5u = view.findViewById(R.id.buff_artifact_5u);

        Button buff_ok = view.findViewById(R.id.buff_ok);
        Button buff_cancel = view.findViewById(R.id.buff_cancel);

        ArrayAdapter sec_aa = new ArrayAdapter(context,R.layout.spinner_item,artifactSecBuffList);
        sec_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sec_sp.setAdapter(sec_aa);

        //if(artifactBasicBaseHP.get(0).isNaN()){}
        // init -- TMP -- Will change after function done
        // Note : CritDMG = 5*5.4% = 27.0% , in this case, the number "5" means the rare of this artifact.

        sec_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                buff_artifact_1t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_1t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_2t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_2t.setChecked(true);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_3t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_3t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_4t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_4t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_1t.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });


                buff_artifact_1u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_1u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_2u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_2u.setChecked(true);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_3u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_3u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_4u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_4u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });
                buff_artifact_5u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_5u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
                    }
                });

                secSetShow(sec_id,i,pos_id,valueTV,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buff_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                artifactBuffSec1Item = artifactBuffSec1ItemTMP;
                artifactBuffSec1Value = artifactBuffSec1ValueTMP;
                artifactBuffSec2Item = artifactBuffSec2ItemTMP;
                artifactBuffSec2Value = artifactBuffSec2ValueTMP;
                artifactBuffSec3Item = artifactBuffSec3ItemTMP;
                artifactBuffSec3Value = artifactBuffSec3ValueTMP;
                artifactBuffSec4Item = artifactBuffSec4ItemTMP;
                artifactBuffSec4Value = artifactBuffSec4ValueTMP;

                showResult(tmp_id);

                // Send current result to buffCal
                buffCal.artifact_setup(tmp_artifact_sort,tmp_artifact_lvl,artifactBuffMainValue,artifactBuffMainItem,artifactBuffSec1Value,artifactBuffSec1Item,artifactBuffSec2Value,artifactBuffSec2Item,artifactBuffSec3Value,artifactBuffSec3Item,artifactBuffSec4Value,artifactBuffSec4Item);

                dialog.dismiss();
            }
        });

        buff_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()*0.9);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void ArtifactBuffView(String type){
        readBasicTier();
        int tmp_id = 0;

        switch (type){
            case "Flower" : {
                showResult(0);
                buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(1,1,0);
                    }
                });
                buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(1,2,0);
                    }
                });
                buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(1,3,0);
                    }
                });
                buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(1,4,0);
                    }
                });
                tmp_id = 0;
                artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                break;
            }
            case "Plume" : {
                showResult(1);
                buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(2,1,1);
                    }
                });
                buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(2,2,1);
                    }
                });
                buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(2,3,1);
                    }
                });
                buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(2,4,1);
                    }
                });
                tmp_id = 1;
                artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                break;
            }
            case "Sand" : {
                showResult(2);
                buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(3,1,2);
                    }
                });
                buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(3,2,2);
                    }
                });
                buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(3,3,2);
                    }
                });
                buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(3,4,2);
                    }
                });
                tmp_id = 2;
                artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                break;
            }
            case "Goblet" : {
                showResult(3);
                buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(4,1,3);
                    }
                });
                buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(4,2,3);
                    }
                });
                buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(4,3,3);
                    }
                });
                buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(4,4,3);
                    }
                });
                tmp_id = 3;
                artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                break;
            }
            case "Circlet" : {
                showResult(4);
                buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(5,1,4);
                    }
                });
                buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(5,2,4);
                    }
                });
                buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(5,3,4);
                    }
                });
                buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BuffDialogDisplay(5,4,4);
                    }
                });
                tmp_id = 4;
                artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                break;
            }
        }

    }

    public void artifactBufSetting(int tmp_id, int[] tmp_artifact_lvl, int[] tmp_artifact_rare){
        String[] artifactBuffList = new String[]{context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_atk)};

        artifactBuffMainItem.set(0,artifactBuffList[0]);
        artifactBuffMainItem.set(1,artifactBuffList[1]);
        artifactBuffMainValue.set(0,artifactBasicBaseHP.get(tmp_artifact_lvl[0]));
        artifactBuffMainValue.set(1,artifactBasicBaseATK.get(tmp_artifact_lvl[1]));

        if(tmp_id == 2){
            artifactBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP)};
        }else if(tmp_id == 3){
            artifactBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EleDMGP_Electro),context.getString(R.string.weapon_stat_EleDMGP_Pyro),context.getString(R.string.weapon_stat_EleDMGP_Hydro),context.getString(R.string.weapon_stat_EleDMGP_Cryo),context.getString(R.string.weapon_stat_EleDMGP_Anemo),context.getString(R.string.weapon_stat_EleDMGP_Geo),context.getString(R.string.weapon_stat_PhyDMGP)};
        }else if(tmp_id == 4){
            artifactBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};
        }



        ArrayAdapter artifact_aa = new ArrayAdapter(context,R.layout.item_spinner_base,artifactBuffList);
        artifact_aa.setDropDownViewResource(R.layout.item_spinner);

        buff_artifact_main_name_sp.setAdapter(artifact_aa);
        String[] finalArtifactBuffList = artifactBuffList;

        if(tmp_id != 0 && tmp_id != 1){
            buff_artifact_main_name.setText(artifactBuffMainItem.get(tmp_id));
            buff_artifact_main_name.setVisibility(View.GONE);
            buff_artifact_main_name_sp.setVisibility(View.VISIBLE);

            buff_artifact_main_name_sp.setSelection(0);
            for (int x = 0 ;x < 5 ; x++){
                if(artifactBuffMainItem.get(tmp_id).equals(artifactBuffList[x])){
                    buff_artifact_main_name_sp.setSelection(x);
                }
            }
        }else{
            buff_artifact_main_name.setText(artifactBuffList[tmp_id]);
            buff_artifact_main_name.setVisibility(View.VISIBLE);
            buff_artifact_main_name_sp.setVisibility(View.GONE);
        }


        if(artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP))){
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),0));
        }else{
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),1));
        }

        buff_artifact_main_name_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                artifactBuffMainTranslate(finalArtifactBuffList,position,buff_artifact_main_value,tmp_id,tmp_artifact_lvl,tmp_artifact_rare);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void artifactBuffMainTranslate(String[] finalArtifactBuffList, int position, TextView buff_artifact_main_value, int tmp_id,int[] tmp_artifact_lvl, int[] tmp_artifact_rare) {
        ArrayList<Double> finalType = new ArrayList<>();
        String type = "SLEEP";

        if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_atkP))){
            type = "ATK";
            finalType = artifactBasicATK;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_HPP))){
            type = "HP";
            finalType = artifactBasicHP;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_DEFP))){
            type = "DEF";
            finalType = artifactBasicDEF;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleMas))){
            type = "EleMas";
            finalType = artifactBasicEleMas;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EnRechP))){
            type = "EnRech";
            finalType = artifactBasicEnRech;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Electro))){
            type = "EleDMG_Electro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Pyro))){
            type = "EleDMG_Pyro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Hydro))){
            type = "EleDMG_Hydro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Cryo))){
            type = "EleDMG_Cryo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Anemo))){
            type = "EleDMG_Anemo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Geo))){
            type = "EleDMG_Geo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_EleDMGP_Dendor))){
            type = "EleDMG_Dendor";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_PhyDMGP))){
            type = "PhyDMG";
            finalType = artifactBasicPhyDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_CritDMGP))){
            type = "CritDMG";
            finalType = artifactBasicCritDMG;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_CritRateP))){
            type = "CritRate";
            finalType = artifactBasicCritRate;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_atk))){
            type = "baseATK";
            finalType = artifactBasicBaseATK;
        }else if(finalArtifactBuffList[position].equals(context.getString(R.string.weapon_stat_HP))){
            type = "baseHP";
            finalType = artifactBasicBaseHP;
        }

        System.out.println(Arrays.toString(finalArtifactBuffList));

        artifactBuffMainItem.set(tmp_id,finalArtifactBuffList[position]);
        artifactBuffMainValue.set(tmp_id,finalType.get(tmp_artifact_lvl[tmp_id]));

        if(artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_EleMas))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_atk))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_DEF))||artifactBuffMainItem.get(tmp_id).equals(context.getString(R.string.weapon_stat_HP))){
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),0));
        }else{
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),1));
        }
    }


    /** UI -> PrettyCount */
    /**
     * @param type : Type of number's display
     *             0 : Int display
     *             1 : Percentage display
     * @return
     */
    public String prettyCount(Number number,int type) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        double numDouble = number.doubleValue();
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num =  sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);

        switch (type){
            case 0 : return plus+new DecimalFormat("###,###,###,###,###").format(number);
            case 1 : return plus+(new DecimalFormat("###,###,###,###,###.#").format(numDouble*100))+"%";
            default: return plus+new DecimalFormat("###,###,###,###,###.#").format(number);
        }
    }

    public void setDBName(String dataSheetName) {
        this.dataSheetName = dataSheetName;
    }

    public void readBasicTier(){
        String json_base = LoadData("db/buff/artifact_basic_tier.json");
        try {
            JSONObject jsonObject = new JSONObject(json_base);
            JSONObject basic = jsonObject.getJSONObject("Basic");

            JSONArray baseHP = basic.getJSONArray("baseHP");
            JSONArray baseATK = basic.getJSONArray("baseATK");
            JSONArray HP = basic.getJSONArray("HP");
            JSONArray ATK = basic.getJSONArray("ATK");
            JSONArray DEF = basic.getJSONArray("DEF");
            JSONArray EleMas = basic.getJSONArray("EleMas");
            JSONArray EnRech = basic.getJSONArray("EnRech");
            JSONArray PhyDMG = basic.getJSONArray("PhyDMG");
            JSONArray EleDMG = basic.getJSONArray("EleDMG");
            JSONArray CritRate = basic.getJSONArray("CritRate");
            JSONArray CritDMG = basic.getJSONArray("CritDMG");
            JSONArray Healing = basic.getJSONArray("Healing");

            JSONObject tier = jsonObject.getJSONObject("Tier");
            JSONArray TbaseHP = tier.getJSONArray("baseHP");
            JSONArray TbaseATK = tier.getJSONArray("baseATK");
            JSONArray TbaseDEF = tier.getJSONArray("baseDEF");
            JSONArray THP = tier.getJSONArray("HP");
            JSONArray TATK = tier.getJSONArray("ATK");
            JSONArray TDEF = tier.getJSONArray("DEF");
            JSONArray TEleMas = tier.getJSONArray("EleMas");
            JSONArray TEnRech = tier.getJSONArray("EnRech");
            JSONArray TCritRate = tier.getJSONArray("CritRate");
            JSONArray TCritDMG = tier.getJSONArray("CritDMG");

            for (int i = 0 ; i < baseHP.length() ; i++){
                artifactBasicBaseHP.add(i,baseHP.getDouble(i));
                artifactBasicBaseATK.add(i,baseATK.getDouble(i));
                artifactBasicHP.add(i,HP.getDouble(i));
                artifactBasicATK.add(i,ATK.getDouble(i));
                artifactBasicDEF.add(i,DEF.getDouble(i));
                artifactBasicEleMas.add(i,EleMas.getDouble(i));
                artifactBasicEnRech.add(i,EnRech.getDouble(i));
                artifactBasicPhyDMG.add(i,PhyDMG.getDouble(i));
                artifactBasicEleDMG.add(i,EleDMG.getDouble(i));
                artifactBasicCritRate.add(i,CritRate.getDouble(i));
                artifactBasicCritDMG.add(i,CritDMG.getDouble(i));
                artifactBasicHealing.add(i,Healing.getDouble(i));
            }

            for (int i = 0 ; i < TbaseHP.length() ; i++){
                artifactTierBaseHP.add(i,TbaseHP.getDouble(i));
                artifactTierBaseATK.add(i,TbaseATK.getDouble(i));
                artifactTierBaseDEF.add(i,TbaseDEF.getDouble(i));
                artifactTierHP.add(i,THP.getDouble(i));
                artifactTierATK.add(i,TATK.getDouble(i));
                artifactTierDEF.add(i,TDEF.getDouble(i));
                artifactTierEleMas.add(i,TEleMas.getDouble(i));
                artifactTierEnRech.add(i,TEnRech.getDouble(i));
                artifactTierCritRate.add(i,TCritRate.getDouble(i));
                artifactTierCritDMG.add(i,TCritDMG.getDouble(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            File file = new File(context.getFilesDir()+"/"+inFile);
            InputStream stream = new FileInputStream(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}
