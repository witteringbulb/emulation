import gifAnimation.*;

final String dataDirectory = dataPath("");

final int IMAGE_BORDER = 100;

final int BRANCH_STROKE_WEIGHT = 255;
final float SIGNAL_STROKE_WEIGHT_SCALING_COEFF = 1;

final int SIGNAL_RED = 255;
final int SIGNAL_GREEN = 255;
final int SIGNAL_BLUE = 255;

final int BRANCH_RED = 100;
final int BRANCH_GREEN = 100;
final int BRANCH_BLUE = 50;

int numberOfFramesInGif = 100;
int startFrame = 0;
int frame;

float[] minMaxXY;

GifMaker gifExport;

void setup() {
  
  size(1000, 1000);
  
  frameRate(12);

  gifExport = new GifMaker(this, "export.gif");
  gifExport.setRepeat(0);        // make it an "endless" animation
  //gifExport.setTransparent(0,0,0);  // black is transparent
  
  resetImageAndRemoveDrawnSignals();
  
  frame = 0;
}

void draw() {
  
  resetImageAndRemoveDrawnSignals();
  
  drawSignals();
  
  gifExport.setDelay(1);
  gifExport.addFrame();
  
  if (frame == numberOfFramesInGif - 1) {
   gifExport.finish();
   exit();
  }
  
  frame++;
  
}

public void resetImageAndRemoveDrawnSignals() {
  fill(#000000, 255);
  rect(0,0,width,height);
  drawBranches();
}

public void drawBranches() {
  Table branches = loadTable("branches.csv", "header");
  for (TableRow row : branches.rows()) {
    float x1 = scaleXValToFit1000by1000Image(row.getFloat("x1"));
    float y1 = scaleYValToFit1000by1000Image(row.getFloat("y1"));
    float x2 = scaleXValToFit1000by1000Image(row.getFloat("x2"));
    float y2 = scaleYValToFit1000by1000Image(row.getFloat("y2"));
    stroke(BRANCH_RED, BRANCH_GREEN, BRANCH_BLUE, BRANCH_STROKE_WEIGHT);
    
    line(x1, y1, x2, y2);
    
  } 
}

public float scaleXValToFit1000by1000Image(float xValueToScale) {
  float x1 = getMinMaxXY()[0];
  float x2 = getMinMaxXY()[2];
  return 1000*(xValueToScale + IMAGE_BORDER - x1)/(2*IMAGE_BORDER + x2 - x1);
}

public float scaleYValToFit1000by1000Image(float yValueToScale) {
  float y1 = getMinMaxXY()[1];
  float y2 = getMinMaxXY()[3];
  return 1000*(yValueToScale + IMAGE_BORDER - y1)/(2*IMAGE_BORDER + y2 - y1);
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
    if (signal.getString("type").equals("SquareSignal")) {drawSquareSignal(signal);}
    else { throw new IllegalArgumentException("Signal type not recognised");}
  }
}

public void drawSquareSignal(TableRow signal) {
  float mean_loc_x = scaleXValToFit1000by1000Image(signal.getFloat("mean_loc_x"));
  float mean_loc_y = scaleYValToFit1000by1000Image(signal.getFloat("mean_loc_y"));
  float direction_x = signal.getFloat("direction_x");
  float direction_y = signal.getFloat("direction_y");
  float amplitude = signal.getFloat("amplitude");
  float signal_width = signal.getFloat("width");
  
  float x1 = mean_loc_x - signal_width * cos(atan(direction_x/direction_y));
  float x2 = mean_loc_x + signal_width * cos(atan(direction_x/direction_y));
  
  float y1 = mean_loc_y - signal_width * sin(atan(direction_x/direction_y));
  float y2 = mean_loc_y + signal_width * sin(atan(direction_x/direction_y));
  
  stroke(SIGNAL_RED, SIGNAL_GREEN, SIGNAL_BLUE, round(amplitude*SIGNAL_STROKE_WEIGHT_SCALING_COEFF));
  
  line(x1, y1, x2, y2);
}
