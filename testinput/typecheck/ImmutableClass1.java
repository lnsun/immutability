package typecheck;

import qual.Immutable;
import qual.Mutable;
import qual.PolyMutable;
import qual.ReceiverDependantMutable;
import qual.Readonly;

@Immutable
class ImmutableClass1{
    // :: error: (type.invalid.annotations.on.use)
    @Mutable ImmutableClass1(Object o) {}
    // :: error: (type.invalid.annotations.on.use)
    @ReceiverDependantMutable ImmutableClass1() {}
    @Immutable ImmutableClass1(@Immutable Number n) {}

    void method1(@Readonly ImmutableClass1 this) {}

    void method2(@Immutable ImmutableClass1 this) {}

    // :: error: (type.invalid.annotations.on.use) :: error: (method.receiver.incompatible)
    void method3(@ReceiverDependantMutable ImmutableClass1 this) {}

    void method4(@PolyMutable ImmutableClass1 this) {}

    // :: error: (method.receiver.incompatible) :: error: (type.invalid.annotations.on.use)
    void method5(@Mutable ImmutableClass1 this) {}

    // Note: the reason why there is no "type.invalid" error
    // TODO Discuss with prof
    // Declared receiver type has "different" types from different perspectives:
    // when PICOVisitor#visitMethod() is called, "this" is defaulted to @Mutable;
    // but when PICOVisitor#visitVariable() is called, "this" inheris @Immutable
    // from its class element. So that's why we get "method.receiver.incompatible"
    // error becasue method receiver is @Mutable, but we didn't get "type.invalid"
    // because @Immutable ImmutableClass1 is the correct usage of ImmutableClass1.
    // See comment: https://github.com/opprop/checker-framework/blob/master/framework/src/org/checkerframework/framework/type/AnnotatedTypeFactory.java#L1593
    // for why class bound annotation is not applied to instance method receiver
    // :: error: (method.receiver.incompatible)
    void method6(ImmutableClass1 this) {}
}
