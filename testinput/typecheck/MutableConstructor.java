package typecheck;

import qual.Mutable;
import qual.Immutable;
import qual.ReceiverDependantMutable;
import qual.Readonly;

public class MutableConstructor {

    @Readonly Object rof;
    @ReceiverDependantMutable Object pif;
    @Immutable Object imf;

    @Mutable MutableConstructor(@Mutable Object mo, @ReceiverDependantMutable Object po, @Immutable Object io) {
        // "this" automatically has the same type as constructor return type.
        this.rof = mo;
        this.rof = po;
        this.rof = io;

        this.pif = mo;
        // :: error: (assignment.type.incompatible)
        this.pif = po;
        // :: error: (assignment.type.incompatible)
        this.pif = io;

        // :: error: (assignment.type.incompatible)
        this.imf = mo;
        // :: error: (assignment.type.incompatible)
        this.imf = po;
        this.imf = io;
    }

    void invokeConstructor(@Mutable Object mo, @ReceiverDependantMutable Object po, @Immutable Object io) {
        new @Mutable MutableConstructor(mo, mo, io);
        // :: error: (argument.type.incompatible)
        new @Mutable MutableConstructor(mo, po, io);
        // The same argument as the one in ImmutableConstructor
        // :: error: (type.invalid.annotations.on.use)
        new @ReceiverDependantMutable MutableConstructor(mo, po, io);
        // :: error: (type.invalid.annotations.on.use)
        new @Immutable MutableConstructor(mo, io, io);
    }
}
