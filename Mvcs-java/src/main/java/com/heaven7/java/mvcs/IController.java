package com.heaven7.java.mvcs;

import java.util.List;

/**
 * a state controller which support multi states.
 * @author heaven7
 *
 * @param <P> the param type.
 */
public interface IController<S extends AbstractState<P>, P> {

    /**
     * set the max state stack size. default max is ten.
     * @param max the max size of state stack.
     */
    void setMaxStateStackSize(int max);

    /**
     * set if enable state stack/history.
     * @param enable true to enable false to disable.
     */
    void setStateStackEnable(boolean enable);

    /**
     * indicate if the state stack is enabled.
     * @return true if the state stack is enabled.
     */
    boolean isStateStackEnable();

    /**
     * clear state stack.
     */
    void clearStateStack();

    /**
     * notify state update by target param.
     * @param param the parameter.
     */
    void notifyStateUpdate(P param);

    /**
     * set share state parameter.
     * @param param the parameter
     */
    void setShareStateParam(P param);

    /**
     * get the share state parameter
     * @return the share state parameter
     */
    P getShareStateParam();
    //==============================================

    /**
     * add states(may be multi) to controller.
     * @param states the new states flags.
     * @param extra the extra state parameter
     * @return true if add the target states success.
     */
	boolean addState(int states, P extra);

    /**
     * add states(may be multi) to controller.
     * @param states the new states flags.
     * @return true if add the target states success.
     */
	boolean addState(int states);
	
	 /**
     * remove the target state from current state.
     * @param states the target state
     * @return true if remove state success. or else this state is not entered,
     * @see {@link #addState(int)}
     */
    boolean removeState(int states);

    /**
     * remove the target state from current state.
     * @param states the target state
     * @param param the extra parameter.
     * @return true if remove state success. or else this state is not entered,
     * @see {@link #addState(int)}
     */
    boolean removeState(int states, P param);

    /**
     * clear the all states
     * @param  param the param which will used by state exit.
     */
    void clearState(P param);
    /**
     * clear the all states
     */
    void clearState();

    /**
     * change to the state
     *
     * @param newStates the new state to change to.
     */
    void setState(int newStates);
    
    /**
     * change to the state
     *
     * @param newStates the new state to change to.
     */
    void setState(int newStates, P extra);
    
    /**
     * Change state back to the previous state.
     *
     * @return {@code True} in case there was a previous state that we were able to revert to. In case there is no previous state,
     * no state change occurs and {@code false} will be returned.
     */
    boolean revertToPreviousState();


    /**
     * set global states
     * @param states the target global states.
     */
    void setGlobalState(int states);
    /**
     * Sets the global state of this state machine.
     *
     * @param states the global state.
     * @param extra the extra parameter
     */
    void setGlobalState(int states, P extra);
    
    /**
     * Indicates whether the state machine is in the given state.
     * <p/>
     * This implementation assumes states are singletons (typically an enum) so
     * they are compared with the {@code ==} operator instead of the
     * {@code equals} method.
     *
     * @param states the state to be compared with the current state
     * @return true if the current state and the given state are the same
     * object.
     */
    boolean isInState(int states);
    
    /**
     * indicate is the target state is acting or not. this is often used in mix state.
     * @param state the target state to check
     * @return true is has the target state.
     */
    boolean hasState(int state);

    /**
     * get the current states.
     * @return the all states if multi. or only contains one.
     */
	List<S> getCurrentStates();

    /**
     * get the current if you use single state.
     * @return the current single state.
     */
	S getCurrentState();

	  /**
     * lock the event
     * @param eventKey  the event key
     * @return true if lock success. false if is already locked.
     */
    boolean lockEvent(int eventKey);

    /**
     * unlock the event .
     * @param eventKey the event key
     * @return true if unlock the event success. false if is not locked.
     */
    boolean unlockEvent(int eventKey);

    /**
     * is the event locked.
     * @param eventKey  the event key
     * @return true if is locked. false otherwise.
     */
    boolean isLockedEvent(int eventKey);
    
    /**
     * set the state factory
     * @param factory the state factory.
     */
    void setStateFactory(StateFactory<S,P> factory);

    /**
     * set the param merger.
     * @param merger the target merger.
     */
    void setParameterMerger(ParameterMerger<P> merger);
    
    
    interface StateFactory<S extends AbstractState<P>, P>{
    	
    	S createState(int stateKey, P p);
    }
    
}