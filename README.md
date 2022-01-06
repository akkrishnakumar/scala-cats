# Cats in Scala

Tests and sample usage of basic types from Cats Library.

## Data Types Used

1. Show[A] - Print content on console without using toString.
2. Eq[A] - type safe equality checker/operator.
3. SemiGroup[A] - type with combine method.
4. Monoid[A] - a semigroup with empty(identity) method. 
5. Functor[F[_]] - type with map function
6. Applicative[F[_]] - type which is a Functor and has pure method
6. Monad[M[_]] - type with pure (can be a Applicative also) and flatMap function.
7. Eval Monad - 
    1. Eval.now - Eager Evaluation (call-by-value)
    2. Eval.always - Lazy Evaluation (call-by-name)
    3. Eval.later - Memoized (call-by-need)
    4. Eval.defer - Stack safe for huge computation.
8. Writer Monad (Writer[Vector[String], A]) - type to carry log messages along.
9. Reader Monad (Reader[A, B]) - sequence operations which require a specific input.
10. State Monad (S => (S,A)) - monad to pass around state



