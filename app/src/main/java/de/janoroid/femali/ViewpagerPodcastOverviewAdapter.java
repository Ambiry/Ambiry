package de.janoroid.femali;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewpagerPodcastOverviewAdapter extends FragmentStateAdapter {

    // The Adapter is for the PodcastOverviewFragment
    public ViewpagerPodcastOverviewAdapter(@NonNull FragmentManager fragmentActivity , Lifecycle lifecycle) {
        super(fragmentActivity,lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

       if (position==1){

        return new AboutThePodcastFragment();

       }else if (position==2){

        return new SimilarPodcastsFragment();

       }
       return new PodcastEpisodeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
