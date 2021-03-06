package se.kth.csc.iprog.agendabuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AgendaModel extends Observable {

	List<Day> days = new ArrayList<Day>();
	List<Activity> parkedActivites = new ArrayList<Activity>();
	
	/**
	 * adds create and add a new day to model with starting time (hours and minutes)
	 */
	public Day addDay(int startHour, int startMin) {
		Day d = new Day(startHour, startMin);
		days.add(d);
		return d;
	}
	
	/**
	 * add an activity to model
	 */
	public void addActivity(Activity act, Day day, int position) {
		if(!day.activities.contains(act)){
			day.addActivity(act, position);
			setChanged();
			notifyObservers(day);
			if(parkedActivites.contains(act))
				removeParkedActivity(parkedActivites.indexOf(act));
			else{
				for(Day d :days){
					if(d.getID()!= day.getID() & d.activities.contains(act)){
						d.removeActivity(d.activities.indexOf(act));
						setChanged();
						notifyObservers("ActivityRemoved");
					}
				}
			}
		}
	}
	
	public void removeActivity(Activity act){
		for(Day d: days){
			if(d.activities.contains(act)){
				d.removeActivity(d.activities.indexOf(act));
			}
		}
	}
	
	
	/**
	 * add an activity to parked activities
	 */
	public void addParkedActivity(Activity act) {
		parkedActivites.add(act);
		for(Day d :days){
			if(d.activities.contains(act)){
				d.removeActivity(d.activities.indexOf(act));
				setChanged();
				notifyObservers("ActivityRemoved");
			}
		}
		setChanged();
		notifyObservers(parkedActivites);
	}
	
	/**
	 * remove an activity on provided position from parked activites 
	 */
	public Activity removeParkedActivity(int position) {
		Activity act =  parkedActivites.remove(position);
		setChanged();
		notifyObservers(act);
		return act;
	}
	
	/**
	 * moves activity between the days, or day and parked activities.
	 * to park activity you need to set the newday to null
	 * to move a parked activity to let's say first day you set oldday to null
	 * and newday to first day instance
	 */
	public void moveActivity(Day oldday, int oldposition, Day newday, int newposition) {
		if(oldday != null && oldday == newday) {
			oldday.moveActivity(oldposition,newposition);
			setChanged();
			notifyObservers("ActivityMoved");
		} else if(oldday == null && newday != null) {
			Activity act = removeParkedActivity(oldposition);
			newday.addActivity(act,newposition);
		} else if(oldday != null && newday == null){
			Activity act = oldday.removeActivity(oldposition);
			addParkedActivity(act);
		} else if(oldday != null && newday != null) {
			Activity activity = oldday.removeActivity(oldposition);
			newday.addActivity(activity,newposition);
		}
	};
	
	public List<Activity> getParkedActivites(){
		return parkedActivites;
	}
	
	/**
	 * you can use this method to create some test data and test your implementation
	 */
	public static AgendaModel getModelWithExampleData() {
		AgendaModel model = new AgendaModel();
		
		Day d = model.addDay(8,0);
		model.addActivity(new Activity("Introduction","Intro to the meeting",10,0),d,0);
		model.addActivity(new Activity("Idea 1","Presenting idea 1",30,0),d,1);
		model.addActivity(new Activity("Working in groups","Working on business model for idea 1",35,1),d,2);
		model.addActivity(new Activity("Idea 1 discussion","Discussing the results of idea 1",15,2),d,3);
		model.addActivity(new Activity("Coffee break","Time for some coffee",20,3),d,4);
		
		return model;
	}
	
}
