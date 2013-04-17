package com.teamdev.students.kasianov.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class AbstractFiniteStateMachine<State extends Enum,
        Context extends FiniteStateMachineContext<State, ?, ?>,
        Matrix extends TransitionMatrix<State>,
        Recognizer extends StateRecognizer<State, Context>,
        TransitionError extends Exception>
        implements FiniteStateMachine<State, Context, Matrix, Recognizer, TransitionError> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFiniteStateMachine.class);

    @Override
    public boolean moveForward(Context context) {
        final Matrix matrix = getTransitionMatrix();
        final Recognizer recognizer = getStateRecognizer();
        for (State state : matrix.getPossibleStates(context.getState())) {
            if (recognizer.accept(state, context)) {
                if(context.isInErrorState()){
                    return false;
                }
                context.setState(state);
                return true;
            }
        }
        //no possible state was accepted
        context.setInErrorState();
        //try to accept one of the rest states to find out the unexpected element, if there is one
        //if Recognizer accepts a state in errorState (context.isInErrorState() = true after context.setInErrorState()),
        //then the error message is set to the context
        //otherwise it is an unknown error
        Set<State> restStates = matrix.getRestStates(matrix.getPossibleStates(context.getState()));
        for(State state : restStates){
            if(recognizer.accept(state,context)){
                break;
            }
        }
        return false;
    }

    @Override
    public void run(Context context) throws TransitionError {
        LOGGER.info("State machine started");
        final Matrix matrix = getTransitionMatrix();
        final State startState = matrix.getStartState();
        context.setState(startState);
        while (!matrix.isFinishState(context.getState())) {
            LOGGER.info("Current context state: " + context.getState());
            if (!moveForward(context)) {
                LOGGER.warn("An error occurred on state: " + context.getState());
                deadlock(context);
                break;
            }
        }
    }
}
