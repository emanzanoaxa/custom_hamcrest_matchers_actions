
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Created by emanzano on 14/03/2016.
 */
public class CustomViewAction {
    /**
     * Click child view with id view action.
     *
     * @param id the id
     * @return the view action
     */
    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) {
                    v.performClick();
                }
            }
        };
    }

    /**
     * Click page child at view action.
     *
     * @param position the position
     * @return the view action
     */
    public static ViewAction clickPageChildAt(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Click click on a page at position specified";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ViewPager pager = (ViewPager) view;
                pager.getChildAt(position).performClick();
            }
        };
    }

    /**
     * Move pager at page view action.
     *
     * @param position the position
     * @return the view action
     */
    public static ViewAction movePagerAtPage(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Click click on a page at position specified";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ViewPager pager = (ViewPager) view;
                pager.setCurrentItem(position);
            }
        };
    }
}
