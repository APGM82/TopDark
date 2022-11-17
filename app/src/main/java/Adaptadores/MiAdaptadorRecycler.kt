import Modelo.Mision

import android.content.Context
import android.content.DialogInterface
import android.content.Intent

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.ActivityDetalles
import com.example.topdark.ActivitySimulacion
import com.example.topdark.R



class MiAdaptadorRecycler(var misiones : ArrayList<Mision>,var  context: Context) : RecyclerView.Adapter<MiAdaptadorRecycler.ViewHolder>() {

    companion object {

        var seleccionado:Int = -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = misiones.get(position)
        holder.bind(item, context, position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card,parent,false))
    }

    override fun getItemCount(): Int {

        return misiones.size
    }


    //--------------------------------- Clase interna ViewHolder -----------------------------------

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val idMision = view.findViewById(R.id.txtIdMision) as TextView
        val nombrePiloto = view.findViewById(R.id.txtNombrePiloto) as TextView
        val nombreNave = view.findViewById(R.id.txtNombreNave) as TextView
        val avatar = view.findViewById(R.id.imgImagen) as ImageView


        fun bind(mis: Mision, context: Context, pos: Int, miAdaptadorRecycler: MiAdaptadorRecycler){
            var nombre=mis.asignacionP

            idMision.text=mis.id.toString()
            nombrePiloto.text = mis.asignacionP
            nombreNave.text = mis.asignacionN
            if (mis.completada==1){
                val uri = "@drawable/completada"
                val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
                var res: Drawable = context.resources.getDrawable(imageResource)
                avatar.setImageDrawable(res)
            }else{
                val uri = "@drawable/incompleta"
                val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
                var res: Drawable = context.resources.getDrawable(imageResource)
                avatar.setImageDrawable(res)
            }
            //Para marcar o desmarcar al seleccionado usamos el siguiente código.
            if (pos == seleccionado) {
                with(nombrePiloto) {
                    this.setTextColor(resources.getColor(R.color.purple_200))
                }
            }
            else {
                with(nombrePiloto) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
            }
            //Se levanta una escucha para cada item. Si pulsamos el seleccionado pondremos la selección a -1, en otro caso será el nuevo sleccionado.
            itemView.setOnClickListener(View.OnClickListener
            {
                if (pos == seleccionado) {
                    seleccionado = -1
                } else {
                    seleccionado = pos

                    var intentV1 = Intent(context, ActivityDetalles::class.java)
                    intentV1.putExtra("id",mis.id.toString())
                    context.startActivity(intentV1)
                }
                //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.


                miAdaptadorRecycler.notifyDataSetChanged()
            })
            itemView.setOnLongClickListener(View.OnLongClickListener
            {
                if (pos == seleccionado){
                    seleccionado = -1
                }
                else {
                    seleccionado = pos
                    val builder = AlertDialog.Builder(context)

                    with(builder)
                    {
                        setTitle("Lanzar misión")
                        setMessage("¿Quiere realizar la misión?")
                        //Otra forma es definir directamente aquí lo que se hace cuando se pulse.
                        setPositiveButton("OK", DialogInterface.OnClickListener(function = { dialog: DialogInterface, which: Int ->
                            var intentV1 = Intent(context, ActivitySimulacion::class.java)
                            intentV1.putExtra("idMision",mis.id.toString())
                            context.startActivity(intentV1)
                        }))
                        setNegativeButton("No", ({ dialog: DialogInterface, which: Int ->

                        }))
                        show()
                    }
                }
                //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
                miAdaptadorRecycler.notifyDataSetChanged()
                return@OnLongClickListener true
            })

        }
    }
}
