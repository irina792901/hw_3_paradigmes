/*Паттерн Memento - это поведенческий паттерн проектирования, который используется
для сохранения внутреннего состояния объекта так, чтобы впоследствии можно было
восстановить объект в это состояние. Этот паттерн предоставляет способ создания снимков
состояния объекта (моментов) и сохранения их для будущего восстановления.
 */


/*Это класс, представляющий состояние объекта, который нужно сохранять.
В нем будет метод для создания снимка состояния (момента) и метод для восстановления состояния из снимка.
*/
class Originator(var state: String) {
    fun createMemento(): Memento {
        return Memento(state)
    }

    fun restoreFromMemento(memento: Memento) {
        state = memento.state
    }
}

/*Это класс `Memento`, который представляет собой снимок состояния объекта `Originator`.
Этот класс имеет только одно поле - состояние.
 */

class Memento(val state: String)


//*Это класс `Caretaker`, который будет отвечать за сохранение и хранение снимков состояния объекта `Originator`.

class Caretaker {
    private val mementos = mutableListOf<Memento>()

    fun addMemento(memento: Memento) {
        mementos.add(memento)
    }

    fun getMemento(index: Int): Memento {
        return mementos[index]
    }
}

//Собственно, применение паттерна memento
fun main() {
    val originator = Originator("Состояние 1")
    val caretaker = Caretaker()

    // Сохраняем текущее состояние
    caretaker.addMemento(originator.createMemento())

    // Меняем состояние
    originator.state = "Состояние 2"

    // Сохраняем ещё одно состояние
    caretaker.addMemento(originator.createMemento())

    // Восстанавливаем первое состояние
    originator.restoreFromMemento(caretaker.getMemento(0))

    println("Текущее состояние: ${originator.state}")
}
/* Этот код создает объект `Originator`, сохраняет его состояние в `Caretaker`, изменяет состояние
и затем восстанавливает предыдущее состояние из `Caretaker`. В результате, текущее состояние объекта
`Originator` будет восстановлено из снимка, и `println` выведет "Текущее состояние: Состояние 1".
 */