package core.ddd

/**
 * @author Clément Garbay
 */
interface AggregateFactory<I : AggregateId, T : AggregateRoot<I, T>> : Factory<I, T>