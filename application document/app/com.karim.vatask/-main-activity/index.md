[app](../../index.md) / [com.karim.vatask](../index.md) / [MainActivity](./index.md)

# MainActivity

`class MainActivity : AppCompatActivity, LocationListener`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MainActivity()` |

### Properties

| Name | Summary |
|---|---|
| [MY_PERMISSIONS_REQUEST_LOCATION](-m-y_-p-e-r-m-i-s-s-i-o-n-s_-r-e-q-u-e-s-t_-l-o-c-a-t-i-o-n.md) | `val MY_PERMISSIONS_REQUEST_LOCATION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [fnumberQueue](fnumber-queue.md) | `lateinit var fnumberQueue: `[`Queue`](https://developer.android.com/reference/java/util/Queue.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [gridAdapter](grid-adapter.md) | `lateinit var gridAdapter: `[`Adapter`](../../com.karim.vatask.-adpter/-adapter/index.md) |
| [items](items.md) | `lateinit var items: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [lastOperation](last-operation.md) | `var lastOperation: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [nightMode](night-mode.md) | `var nightMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [op](op.md) | `var op: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [opQueue](op-queue.md) | `lateinit var opQueue: `[`Queue`](https://developer.android.com/reference/java/util/Queue.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [snumberQueue](snumber-queue.md) | `lateinit var snumberQueue: `[`Queue`](https://developer.android.com/reference/java/util/Queue.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [buttonClick](button-click.md) | `fun buttonClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is the all onClick actions in the all application with specific time given to handle it in the background ... which depend on the text inside the view ..... if the text -&gt; "=" ........ we check either the op variable which contain the current operation is empty or     no if not we check the entered number of the user if exist then we run evaluate  function ....... else we save the view text to the op variable |
| [getGPS](get-g-p-s.md) | `fun getGPS(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onLocationChanged](on-location-changed.md) | `fun onLocationChanged(location: `[`Location`](https://developer.android.com/reference/android/location/Location.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
