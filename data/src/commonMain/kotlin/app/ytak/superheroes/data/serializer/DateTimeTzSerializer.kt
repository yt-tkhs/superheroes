package app.ytak.superheroes.data.serializer

import com.soywiz.klock.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializer(forClass = DateTimeTz::class)
object DateTimeTzSerializer : KSerializer<DateTimeTz> {

    private val dateFormat by lazy { DateFormat("yyyy-MM-dd'T'HH:mm:ssZ") }

    override val descriptor: SerialDescriptor =
        PrimitiveDescriptorWithName("DateTimeTzSerializer", StringDescriptor)

    override fun deserialize(decoder: Decoder): DateTimeTz =
        try {
            dateFormat.parse(decoder.decodeString())
        } catch (e: Throwable) {
            DateTime.EPOCH.toOffset(TimeSpan.ZERO)
        }

    override fun serialize(encoder: Encoder, obj: DateTimeTz) {
        encoder.encodeString(DateFormat.FORMAT1.withLocale(KlockLocale.english).format(obj))
    }
}
