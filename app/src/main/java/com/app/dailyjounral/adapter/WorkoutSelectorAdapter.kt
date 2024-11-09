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
import com.app.dailyjounral.adapter.WorkoutDetectorItemViewHolder
import com.app.dailyjounral.databinding.LayoutWorkoutDetectorBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.getWorkoutDataResponse.SetSelectedWorkoutData
import com.app.dailyjounral.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WorkoutSelectorAdapter(
    val context: Context,
    private val list: MutableList<SetSelectedWorkoutData>,
    private val selectedWorkoutTypeId: MutableLiveData<Int>,
    val detailViewModel: DetailViewModel,
    val onItemSelected: OnItemSelected<SetSelectedWorkoutData>
):  RecyclerView.Adapter<WorkoutDetectorItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutDetectorItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<LayoutWorkoutDetectorBinding>(
            layoutInflater,
            R.layout.layout_workout_detector,
            parent,
            false
        )
        return WorkoutDetectorItemViewHolder(binder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WorkoutDetectorItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
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
            detailViewModel.saveWorkoutApiResponse(list[position].typeId)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}