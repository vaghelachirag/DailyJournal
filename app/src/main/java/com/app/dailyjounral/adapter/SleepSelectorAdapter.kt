import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.SleepDetectorItemViewHolder
import com.app.dailyjounral.adapter.WorkoutDetectorItemViewHolder
import com.app.dailyjounral.databinding.LayoutSleepDetectorBinding
import com.app.dailyjounral.databinding.LayoutWorkoutDetectorBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.getSleepDataResponse.SetSelectedSleepData
import com.app.dailyjounral.model.getWorkoutDataResponse.SetSelectedWorkoutData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SleepSelectorAdapter(val context: Context, private val list: MutableList<SetSelectedSleepData>, private val selectedWorkoutTypeId: MutableLiveData<Int>, val onItemSelected: OnItemSelected<SetSelectedSleepData>):  RecyclerView.Adapter<SleepDetectorItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepDetectorItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<LayoutSleepDetectorBinding>(
            layoutInflater,
            R.layout.layout_sleep_detector,
            parent,
            false
        )
        return SleepDetectorItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SleepDetectorItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])

        holder.binding.llMood.visibility = View.VISIBLE
        holder.binding.ivIcon.visibility = View.GONE

        Log.e("SelectedMoodId",selectedWorkoutTypeId.value.toString())

        val options: RequestOptions = RequestOptions()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context).load(list[position].image).apply(options).into(holder.binding.ivMood)

        if (list[position].typeId == selectedWorkoutTypeId.value){
            holder.binding.rbSelection.isChecked = true
        }
        else{
            holder.binding.rbSelection.isChecked = false
        }

        holder.binding.rbSelection.setOnClickListener {
          //  detailViewModel.saveMoodApiResponse(list[position].typeId)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}