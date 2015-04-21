package sdg.speedy_desired_graph;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


public class MainActivity extends ActionBarActivity {

    public static StartActivity frag0;
    public static SetValueActivity frag1;
    public static GraphActivity frag2;
    public static DataActivity frag3;
    public static SelectActivity frag4;
    public static XFunctionActivity frag5;
    public static AboutActivity frag6;
    public static MenuListActivity frag7;
    public static Graph2Activity frag8;
    public static FragmentTransaction fTrans;
    private Drawer.Result drawerResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag0 = new StartActivity();
        frag1 = new SetValueActivity();
        frag2 = new GraphActivity();
        frag3 = new DataActivity();
        frag4 = new SelectActivity();
        frag5 = new XFunctionActivity();
        frag6 = new AboutActivity();
        frag7 = new MenuListActivity();
        frag8 = new Graph2Activity();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.set_value).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName(R.string.menulist_title).withIcon(FontAwesome.Icon.faw_gamepad).setEnabled(SetValueActivity.enable),
                        new PrimaryDrawerItem().withName(R.string.graph_title).withIcon(FontAwesome.Icon.faw_gamepad).setEnabled(SetValueActivity.enable),
                        new PrimaryDrawerItem().withName(R.string.data_title).withIcon(FontAwesome.Icon.faw_eye).setEnabled(SetValueActivity.enable),
                        new PrimaryDrawerItem().withName(R.string.select_title).withIcon(FontAwesome.Icon.faw_gamepad).setEnabled(SetValueActivity.enable),
                        new PrimaryDrawerItem().withName(R.string.xfunction_title).withIcon(FontAwesome.Icon.faw_gamepad).setEnabled(SetValueActivity.enable),
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_cog).setEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_question).setEnabled(false),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.about_title).withIcon(FontAwesome.Icon.faw_github)
                )

                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        displayView(position);
                        if(drawerItem instanceof Nameable)

                        {
                            Toast.makeText(MainActivity.this, MainActivity.this.getString(((Nameable) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    private void enable(int position) {
                        switch (position) {
                            case 0:

                                break;
                        }
                    }

                    private void displayView(int position) {
                        fTrans = getFragmentManager().beginTransaction();
                        switch (position) {
                            case 0:
                                fTrans.replace(R.id.frgmCont, frag0);
                                fTrans.commit();
                                break;
                            case 1:
                                fTrans.replace(R.id.frgmCont, frag1);
                                fTrans.commit();
                                break;
                            case 2:
                                fTrans.replace(R.id.frgmCont, frag7);
                                fTrans.commit();
                                break;
                            case 3:
                                fTrans.replace(R.id.frgmCont, frag2);
                                fTrans.commit();
                                break;
                            case 4:
                                fTrans.replace(R.id.frgmCont, frag3);
                                fTrans.commit();
                                break;
                            case 5:
                                fTrans.replace(R.id.frgmCont, frag4);
                                fTrans.commit();
                                break;
                            case 6:
                                fTrans.replace(R.id.frgmCont, frag5);
                                fTrans.commit();
                                break;
                            case 11:
                                fTrans.replace(R.id.frgmCont, frag6);
                                fTrans.commit();
                                break;
                            default:
                                break;
                        }
                    }

                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof SecondaryDrawerItem) {
                            Toast.makeText(MainActivity.this, MainActivity.this.getString(((SecondaryDrawerItem) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                })
                .build();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, frag0);
        fTrans.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}