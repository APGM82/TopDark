import Modelo.Mision

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.ActivityDetalles
import com.example.topdark.ActivitySimulacion
import com.example.topdark.R


class MiAdaptadorRecycler(var misiones : ArrayList<Mision>,var  context: Context) : RecyclerView.Adapter<MiAdaptadorRecycler.ViewHolder>() {

    companion object {

        var seleccionado:Int = -1
    }
    /**
     * onBindViewHolder() se encarga de coger cada una de las posiciones de la lista de personajes y pasarlas a la clase
     * ViewHolder(clase interna, ver abajo) para que esta pinte todos los valores y active el evento onClick en cada uno.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = misiones.get(position)
        holder.bind(item, context, position, this)
    }

    /**
     *  Como su nombre indica lo que hará será devolvernos un objeto ViewHolder al cual le pasamos la celda que hemos creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //return ViewHolder(layoutInflater.inflate(R.layout.item_lo,parent,false))
        return ViewHolder(layoutInflater.inflate(R.layout.item_card,parent,false))
    }

    /**
     * getItemCount() nos devuelve el tamaño de la lista, que lo necesita el RecyclerView.
     */
    override fun getItemCount(): Int {

        return misiones.size
    }


    //--------------------------------- Clase interna ViewHolder -----------------------------------
    /**
     * La clase ViewHolder. No es necesaria hacerla dentro del adapter, pero como van tan ligadas
     * se puede declarar aquí.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Esto solo se asocia la primera vez que se llama a la clase, en el método onCreate de la clase que contiene a esta.
        //Por eso no hace falta que hagamos lo que hacíamos en el método getView de los adaptadores para las listsViews.
        //val nombrePersonaje = view.findViewById(R.id.txtNombre) as TextView
        //val tipoPersonaje = view.findViewById(R.id.txtTipo) as TextView
        //val avatar = view.findViewById(R.id.imgImagen) as ImageView

        //Como en el ejemplo general de las listas (ProbandoListas) vemos que se puede inflar cada elemento con una card o con un layout.
        val idMision = view.findViewById(R.id.txtIdMision) as TextView
        val nombrePiloto = view.findViewById(R.id.txtNombrePiloto) as TextView
        val nombreNave = view.findViewById(R.id.txtNombreNave) as TextView
        val avatar = view.findViewById(R.id.imgImagen) as ImageView
        /**
         * Éste método se llama desde el método onBindViewHolder de la clase contenedora. Como no vuelve a crear un objeto
         * sino que usa el ya creado en onCreateViewHolder, las asociaciones findViewById no vuelven a hacerse y es más eficiente.
         */
        fun bind(mis: Mision, context: Context, pos: Int, miAdaptadorRecycler: MiAdaptadorRecycler){
            idMision.text=mis.id.toString()
            nombrePiloto.text = mis.asignacionP
            nombreNave.text = mis.asignacionN

            /*if (pers.nombre.equals("Gandalf")){
                //Para Gandalf, como ejemplo, le he puesto una imagen que se llama igual en el drawable.
                //se podría hacer igual para los otros personajes.

                val uri = "@drawable/" + pers.imagen
                val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
                var res:Drawable = context.resources.getDrawable(imageResource)
                avatar.setImageDrawable(res)
            */


            //Para marcar o desmarcar al seleccionado usamos el siguiente código.
            if (pos == MiAdaptadorRecycler.seleccionado) {
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
                if (pos == MiAdaptadorRecycler.seleccionado){
                    MiAdaptadorRecycler.seleccionado = -1
                }
                else {
                    MiAdaptadorRecycler.seleccionado = pos

                    var intentV1 = Intent(context, ActivityDetalles::class.java)
                    intentV1.putExtra("id",mis.id.toString())
                    context.startActivity(intentV1)
                }
                //Con la siguiente instrucción forzamos a recargar el viewHolder porque han cambiado los datos. Así pintará al seleccionado.
                miAdaptadorRecycler.notifyDataSetChanged()


            })
            itemView.setOnLongClickListener(View.OnLongClickListener
            {
                if (pos == MiAdaptadorRecycler.seleccionado){
                    MiAdaptadorRecycler.seleccionado = -1
                }
                else {
                    MiAdaptadorRecycler.seleccionado = pos
                    val builder = AlertDialog.Builder(context)

                    with(builder)
                    {
                        setTitle("Lanzar misión")
                        setMessage("¿Quiere realizar la misión?")
                        //Otra forma es definir directamente aquí lo que se hace cuando se pulse.
                        setPositiveButton("OK", DialogInterface.OnClickListener(function = { dialog: DialogInterface, which: Int ->
                            var intentV1 = Intent(context, ActivitySimulacion::class.java)
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
