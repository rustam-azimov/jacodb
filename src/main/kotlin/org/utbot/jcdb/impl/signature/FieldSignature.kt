package org.utbot.jcdb.impl.signature

import org.objectweb.asm.signature.SignatureReader
import org.utbot.jcdb.api.ClasspathSet

class FieldSignature : GenericTypeRegistrant {

    private lateinit var fieldType: GenericType

    override fun register(token: GenericType) {
        fieldType = token
    }

    protected fun resolve(): FieldResolution {
        return FieldResolutionImpl(fieldType)
    }

    companion object {
        fun extract(signature: String?, cp: ClasspathSet): FieldResolution {
            signature ?: return Raw
            val signatureReader = SignatureReader(signature)
            val visitor = FieldSignature()
            return try {
                signatureReader.acceptType(GenericTypeExtractor(cp, visitor))
                visitor.resolve()
            } catch (ignored: RuntimeException) {
                Malformed
            }
        }
    }
}