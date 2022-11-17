package Adaptadores

import Modelo.Pilotos
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.R

class MiAdaptadorRecycler2 (var pilotos : ArrayList<Pilotos>, var  context: Context) : RecyclerView.Adapter<MiAdaptadorRecycler2.ViewHolder>() {
    companion object {

        var seleccionado:Int = -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pilotos.get(position)
        holder.bind(item, context, position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card2,parent,false))
    }

    override fun getItemCount(): Int {

        return pilotos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nombrePiloto = view.findViewById(R.id.txtNombrePiloto2) as TextView
        val edad = view.findViewById(R.id.txtEdad) as TextView
        val txtRango=view.findViewById(R.id.txtRango) as TextView
        val txtExperiencia = view.findViewById(R.id.txtExperiencia) as TextView
        val avatar2 = view.findViewById(R.id.imgImagen2) as ImageView


        fun bind(pil: Pilotos, context: Context, pos: Int, miAdaptadorRecycler: MiAdaptadorRecycler2){
            //var nombre=pil.asignacionP
            edad.text=pil.edad.toString()
            nombrePiloto.text = pil.nombre
            txtExperiencia.text=pil.experiencia.toString()
            if (pil.experiencia<50){
                txtRango.text="Novato"
            }
            if (pil.experiencia>99){
                txtRango.text="Intermedio"
            }
            if (pil.experiencia>49&&pil.experiencia<100){
                txtRango.text="Experto"
            }

            if(pil.foto!=null){
                var bitmap1 = BitmapFactory.decodeFile("/storage/emulated/0/Android/data/com.example.topdark/files/"+pil.foto)
                avatar2.setImageBitmap(bitmap1)
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
            /*itemView.setOnClickListener(
                View.OnClickListener
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
            })*/
            /*itemView.setOnLongClickListener(
                View.OnLongClickListener
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
            })*/

        }
    }

}