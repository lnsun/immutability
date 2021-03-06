package typecheck;

import qual.Mutable;
import qual.Immutable;
import qual.ReceiverDependantMutable;
import qual.Readonly;

@ReceiverDependantMutable
public class ReceiverDependantMutableConstructor {

    @Readonly Object rof;
    @ReceiverDependantMutable Object pif;
    @Immutable Object imf;

    // :: error: (initialization.fields.uninitialized)
    @ReceiverDependantMutable ReceiverDependantMutableConstructor(@Mutable Object mo, @ReceiverDependantMutable Object po, @Immutable Object io) {
    }

    @ReceiverDependantMutable ReceiverDependantMutableConstructor(@ReceiverDependantMutable Object po, @Immutable Object io) {
        this.rof = po;
        this.rof = io;

        this.pif = po;
        // :: error: (assignment.type.incompatible)
        this.pif = io;

        // :: error: (assignment.type.incompatible)
        this.imf = po;
        this.imf = io;
    }

    void invokeConstructor(@Readonly Object ro, @Mutable Object mo, @ReceiverDependantMutable Object po, @Immutable Object io) {
        new @Mutable ReceiverDependantMutableConstructor(mo, io);
        // :: error: (argument.type.incompatible)
        new @Mutable ReceiverDependantMutableConstructor(ro, io);
        // :: error: (argument.type.incompatible)
        new @Mutable ReceiverDependantMutableConstructor(po, io);
        // :: error: (argument.type.incompatible)
        new @Mutable ReceiverDependantMutableConstructor(io, io);

        new @ReceiverDependantMutable ReceiverDependantMutableConstructor(po, io);
        // :: error: (argument.type.incompatible)
        new @ReceiverDependantMutable ReceiverDependantMutableConstructor(ro, io);
        // :: error: (argument.type.incompatible)
        new @ReceiverDependantMutable ReceiverDependantMutableConstructor(mo, io);
        // :: error: (argument.type.incompatible)
        new @ReceiverDependantMutable ReceiverDependantMutableConstructor(io, io);

        new @Immutable ReceiverDependantMutableConstructor(io, io);
        // :: error: (argument.type.incompatible)
        new @Immutable ReceiverDependantMutableConstructor(ro, io);
        // :: error: (argument.type.incompatible)
        new @Immutable ReceiverDependantMutableConstructor(mo, io);
        // :: error: (argument.type.incompatible)
        new @Immutable ReceiverDependantMutableConstructor(po, io);

        // :: error: (pico.new.invalid)
        new @Readonly ReceiverDependantMutableConstructor(ro, io);
    }
}
