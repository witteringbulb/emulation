import gifAnimation.*;

final String dataDirectory = dataPath("");

final int IMAGE_BORDER = 100;

final float SIGNAL_OPACITY_BASE = 1;
final int FADE_ALPHA = 20;

final int SIGNAL_EXCITE_RED = 46;
final int SIGNAL_EXCITE_GREEN = 187;
final int SIGNAL_EXCITE_BLUE = 234;

final int SIGNAL_INHIB_RED = round(145*1.5);//234;
final int SIGNAL_INHIB_GREEN = round(113*1.5);//105;
final int SIGNAL_INHIB_BLUE = round(27*1.5);//46;

final int SIGNAL_AXON_RED = 234;
final int SIGNAL_AXON_GREEN = 234;
final int SIGNAL_AXON_BLUE = 234;

int numberOfFramesInVideo = 800;
int startFrame = 0;
int frame;

float[] minMaxXY;

HashMap<Integer, Branch> allBranches;

GifMaker gifExport;

void setup() {
  
  size(1000, 1000);
  
  frameRate(12);
  
  allBranches = loadBranches();
  
  drawBGForFirstTime();
  
  frame = 0;
}

void draw() {
  
  fadeSignals();
  
  drawSignals();
  
  saveFrame("frames"+File.separator+"####.png");
  
  if (frame == numberOfFramesInVideo - 1) {
   exit();
  }
  
  frame++;
  
}

public void drawBGForFirstTime() {
  fill(#000000, 255);
  stroke(#000000, 255);
  rect(0,0,width,height);
}

public void fadeSignals() {
  fill(#000000, FADE_ALPHA);
  stroke(#000000, 255);
  rect(0,0,width,height);
}

public HashMap<Integer, Branch> loadBranches() {
  HashMap<Integer, Branch> branchesMap = new HashMap<Integer, Branch>();
  Table branches = loadTable("branches.csv", "header");
  for (TableRow row : branches.rows()) {
    Branch branch = new Branch(row.getString("branch_type"), row.getFloat("x1"), row.getFloat("y1"), row.getFloat("x2"), row.getFloat("y2"));
    branchesMap.put(row.getInt("branch_uid"), branch);
  }
  return branchesMap;
}

public float[] getMinMaxXY() {
  if (minMaxXY == null) {
    Table branches = loadTable("branches.csv", "header");
    //find max and min values of x and y
    float xmin = 100000;
    float xmax = -100000;
    float ymin = 100000;
    float ymax = -100000;
    for (TableRow row : branches.rows()) {
      if (row.getFloat("x1") < xmin) {xmin = row.getFloat("x1");}
      if (row.getFloat("x2") < xmin) {xmin = row.getFloat("x2");}
      if (row.getFloat("x1") > xmax) {xmax = row.getFloat("x1");}
      if (row.getFloat("x2") > xmax) {xmax = row.getFloat("x2");}
      if (row.getFloat("y1") < ymin) {ymin = row.getFloat("y1");}
      if (row.getFloat("y2") < ymin) {ymin = row.getFloat("y2");}
      if (row.getFloat("y1") > ymax) {ymax = row.getFloat("y1");}
      if (row.getFloat("y2") > ymax) {ymax = row.getFloat("y2");}
    }
    this.minMaxXY = new float[]{xmin, ymin, xmax, ymax};
    
  }
  return this.minMaxXY;
}

public void drawSignals() {
  Table signalsForThisFrame = loadTable(frame+"_allSignals.csv", "header");
  for (TableRow signal : signalsForThisFrame.rows()) {
    drawSignal(signal);
  }
}

public void drawSignal(TableRow signal) {
  int parentBranchUid = signal.getInt("branch_uid");
  float ratioDistanceAlongBranch = signal.getFloat("ratio_distance_along_branch");
  float amplitude = signal.getFloat("amplitude");
  
  if (ratioDistanceAlongBranch <= 1.0) {
    allBranches.get(parentBranchUid).drawSignal(ratioDistanceAlongBranch, amplitude);
  }
}

public float scaleXValToFitImage(float xValueToScale) {
  float x1 = getMinMaxXY()[0];
  float x2 = getMinMaxXY()[2];
  return (width - 2*IMAGE_BORDER)*(xValueToScale - x1)/(x2-x1) + IMAGE_BORDER;
}

public float scaleYValToFitImage(float yValueToScale) {
  float y1 = getMinMaxXY()[1];
  float y2 = getMinMaxXY()[3];
  return (height - 2*IMAGE_BORDER)*(yValueToScale - y1)/(y2-y1) + IMAGE_BORDER;
}

class Branch {
  
  public final String branchType;
  public final float scaledXBeginning;
  public final float scaledYBeginning;
  public final float scaledXEnd;
  public final float scaledYEnd;
  
  public Branch(String branchType, float xBeginning, float yBeginning, float xEnd, float yEnd) {
    this.branchType = branchType;
    this.scaledXBeginning = scaleXValToFitImage(xBeginning);
    this.scaledYBeginning = scaleYValToFitImage(yBeginning);
    this.scaledXEnd = scaleXValToFitImage(xEnd);
    this.scaledYEnd = scaleYValToFitImage(yEnd);
  }
  
  public void drawSignal(float ratioDistanceAlongBranch, float amplitude) {
  
  float xSignal = ratioDistanceAlongBranch * (this.scaledXEnd - this.scaledXBeginning) + this.scaledXBeginning;
  float ySignal = ratioDistanceAlongBranch * (this.scaledYEnd - this.scaledYBeginning) + this.scaledYBeginning; 
  
  float xSignal_interp = (ratioDistanceAlongBranch+0.02) * (this.scaledXEnd - this.scaledXBeginning) + this.scaledXBeginning;
  float ySignal_interp = (ratioDistanceAlongBranch+0.02) * (this.scaledYEnd - this.scaledYBeginning) + this.scaledYBeginning;
    
  strokeWeight(1);
  if (branchType.equals("dendrite") && amplitude >= 0) {
    stroke(color(SIGNAL_EXCITE_RED, SIGNAL_EXCITE_GREEN, SIGNAL_EXCITE_BLUE), max(round(abs(amplitude)*SIGNAL_OPACITY_BASE), 255));
  } else if (branchType.equals("dendrite") && amplitude < 0) {
    stroke(color(SIGNAL_INHIB_RED, SIGNAL_INHIB_GREEN, SIGNAL_INHIB_BLUE), max(round(abs(amplitude)*SIGNAL_OPACITY_BASE), 255));
  } else {
    stroke(color(SIGNAL_AXON_RED, SIGNAL_AXON_GREEN, SIGNAL_AXON_BLUE), max(round(abs(amplitude)*SIGNAL_OPACITY_BASE), 255));
  }

  point(xSignal, ySignal);
  point(xSignal_interp, ySignal_interp);
  }
  
}
