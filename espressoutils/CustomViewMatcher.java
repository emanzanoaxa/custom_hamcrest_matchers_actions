
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * The type Custom view matcher.
 */
public final class CustomViewMatcher {
    private CustomViewMatcher() {
    }

    /**
     * Has any error text bounded matcher.
     *
     * @return the bounded matcher
     */
    public static BoundedMatcher<View, EditText> hasAnyErrorText() {
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            protected boolean matchesSafely(EditText item) {
                return item.getError() != null;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("not showing any error");
            }
        };
    }

    /**
     * At position matcher.
     *
     * @param position    the position
     * @param itemMatcher the item matcher
     * @return the matcher
     */
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    /**
     * At position with id matcher.
     *
     * @param position    the position
     * @param childId     the child id
     * @param itemMatcher the item matcher
     * @return the matcher
     */
    public static Matcher<View> atPositionWithId(final int position, @IdRes int childId,
                                                 @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView.findViewById(childId));
            }
        };
    }

    /**
     * Has items matcher.
     *
     * @param size the size
     * @return the matcher
     */
    public static Matcher<View> hasItems(final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(final View view) {
                return ((RecyclerView) view).getChildCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("RecyclerView should have " + size + " items");
            }
        };
    }

    /**
     * View pager has items matcher.
     *
     * @param size the size
     * @return the matcher
     */
    public static Matcher<View> viewPagerHasItems(final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return ((ViewPager)item).getChildCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("ViewPager should have " + size + " items");
            }
        };
    }

    /**
     * View pager is on position matcher.
     *
     * @param position the position
     * @return the matcher
     */
    public static Matcher<View> viewPagerIsOnPosition(final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return ((ViewPager)item).getCurrentItem() == position;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("ViewPager must be on " + position + " page");
            }
        };
    }

    /**
     * With collapsible toolbar title matcher.
     *
     * @param textMatcher the text matcher
     * @return the matcher
     */
    public static Matcher<Object> withCollapsibleToolbarTitle(final Matcher<String> textMatcher) {
        return new BoundedMatcher<Object, CollapsingToolbarLayout>(CollapsingToolbarLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(CollapsingToolbarLayout toolbarLayout) {
                return textMatcher.matches(toolbarLayout.getTitle());
            }
        };
    }
}