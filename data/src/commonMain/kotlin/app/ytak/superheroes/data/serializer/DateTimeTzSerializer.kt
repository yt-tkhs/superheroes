package app.ytak.superheroes.data.serializer

import com.soywiz.klock.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializer(forClass = DateTimeTz::class)
object DateTimeTzSerializer : KSerializer<DateTimeTz> {

    override val descriptor: SerialDescriptor =
        PrimitiveDescriptorWithName("DateTimeTzSerializer", StringDescriptor)

    override fun deserialize(decoder: Decoder): DateTimeTz =
        try {
            DateFormat.FORMAT1.withLocale(KlockLocale.default).parse(decoder.decodeString())
        } catch (e: Throwable) {
            DateTime.EPOCH.toOffset(TimeSpan.ZERO)
        }

    override fun serialize(encoder: Encoder, obj: DateTimeTz) {
        encoder.encodeString(DateFormat.FORMAT1.withLocale(KlockLocale.english).format(obj))
    }
}
