import gifAnimation.*;

final String dataDirectory = dataPath("");

final int IMAGE_BORDER = 100;

final int BRANCH_ALPHA_BASE = 255;
final float SIGNAL_OPACITY_BASE = 1;
final float SIGNAL_STROKE_WEIGHT_BASE = 10;
final int FADE_ALPHA = 20;

final int SIGNAL_RED = 255;
final int SIGNAL_GREEN = 255;
final int SIGNAL_BLUE = 255;

final int BRANCH_RED = 100;
final int BRANCH_GREEN = 100;
final int BRANCH_BLUE = 50;

int numberOfFramesInGif = 500;
int startFrame = 0;
int frame;

float[] minMaxXY;

HashMap<Integer, Branch> allBranches;

GifMaker gifExport;

void setup() {
  
  size(1000, 1000);
  
  frameRate(12);

  gifExport = new GifMaker(this, "export.gif");
  gifExport.setRepeat(0);        // make it an "endless" animation
  //gifExport.setTransparent(0,0,0);  // black is transparent
  
  allBranches = loadBranches();
  
  drawBranchesForFirstTime();
  
  frame = 0;
}

void draw() {
  
  fadeSignals();
  
  drawSignals();
  
  gifExport.setDelay(1);
  gifExport.addFrame();
  
  if (frame == numberOfFramesInGif - 1) {
   gifExport.finish();
   exit();
  }
  
  frame++;
  
}

public void drawBranchesForFirstTime() {
  fill(#000000, 255);
  rect(0,0,width,height);
  drawBranches(BRANCH_ALPHA_BASE);
}

public void fadeSignals() {
  fill(#000000, FADE_ALPHA);
  rect(0,0,width,height);
  drawBranches(FADE_ALPHA);
}

public void drawBranches(int alpha) {
  for (Branch branch : allBranches.values()) {
    branch.drawBranch(alpha);
  }
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
  
  allBranches.get(parentBranchUid).drawSignal(ratioDistanceAlongBranch, amplitude);
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
  
  public void drawBranch(int alpha) {
    stroke(color(BRANCH_RED, BRANCH_GREEN, BRANCH_BLUE), alpha);
    line(scaledXBeginning, scaledYBeginning, scaledXEnd, scaledYEnd);
  }
  
  public void drawSignal(float ratioDistanceAlongBranch, float amplitude) {
    
  float xSignal = ratioDistanceAlongBranch * (this.scaledXEnd - this.scaledXBeginning) + this.scaledXBeginning;
  float ySignal = ratioDistanceAlongBranch * (this.scaledYEnd - this.scaledYBeginning) + this.scaledYBeginning; 
    
  strokeWeight(round(amplitude * SIGNAL_STROKE_WEIGHT_BASE));
  stroke(color(SIGNAL_RED, SIGNAL_GREEN, SIGNAL_BLUE), max(round(amplitude*SIGNAL_OPACITY_BASE), 255));
  point(xSignal, ySignal);
  }
  
}
