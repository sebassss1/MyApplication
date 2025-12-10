package com.example.myapplication.ui.gallery

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Register both ImageViews for the context menu
        registerForContextMenu(binding.imageView1)
        registerForContextMenu(binding.imageView2)

        // Set up the long-click listener for ActionMode
        binding.imageView1.setOnLongClickListener { view ->
            (activity as? AppCompatActivity)?.startSupportActionMode(actionModeCallback)
            true
        }

        binding.imageView2.setOnLongClickListener { view ->
            (activity as? AppCompatActivity)?.startSupportActionMode(actionModeCallback)
            true
        }

        return root
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.gallery_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(context, "Edit action", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_delete -> {
                Toast.makeText(context, "Delete action", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share -> {
                Toast.makeText(context, "Share action", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private val actionModeCallback = object : androidx.appcompat.view.ActionMode.Callback {
        override fun onCreateActionMode(mode: androidx.appcompat.view.ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.gallery_context_menu, menu)
            mode?.title = "Options"
            return true
        }

        override fun onPrepareActionMode(mode: androidx.appcompat.view.ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: androidx.appcompat.view.ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.action_edit -> {
                    Toast.makeText(context, "Edit action", Toast.LENGTH_SHORT).show()
                    mode?.finish()
                    true
                }
                R.id.action_delete -> {
                    Toast.makeText(context, "Delete action", Toast.LENGTH_SHORT).show()
                    mode?.finish()
                    true
                }
                R.id.action_share -> {
                    Toast.makeText(context, "Share action", Toast.LENGTH_SHORT).show()
                    mode?.finish()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: androidx.appcompat.view.ActionMode?) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}