final String dataDirectory = dataPath("");

final int WIDTH_IN_PIXELS = 1000;
final int HEIGHT_IN_PIXELS = 1000;

int numberOfFramesInGif = 100;
int startFrame = 1;
int frame;

float conversionCoefficientNeuronCoordinatesPerPixelXAxis;
float conversionCoefficientNeuronCoordinatesPerPixelYAxis;

void setup() {
  
  size(WIDTH_IN_PIXELS, HEIGHT_IN_PIXELS);
  
  setConversionCoefficientsUsingBranchInformation();
  
  resetImageAndRemoveDrawnSignals();
  
  frame = 0;
}

void draw() {
  
  resetImageAndRemoveDrawnSignals();
  
  drawSignals();
  
  saveToImage();
  
  if (frame == numberOfFramesInGif) {
   loadSavedImagesAndSaveAsGif(); 
  }
  
  frame++;
  
}

public void resetImageAndRemoveDrawnSignals() {
  fill(#000000, 255);
  rect(0,0,width,height);
  drawBranches();
}

public void drawBranches() {
  Table branches = loadTable("branches.csv");
  for (TableRow row : branches.rows()) {
    float x1 = row.getFloat("x1");
    float y1 = row.getFloat("y1");
    float x2 = row.getFloat("x2");
    float y2 = row.getFloat("y2");
    line(x1, y1, x2, y2);
  } 
}

public void setConversionCoefficientsUsingBranchInformation() {
  Table branches = loadTable("branches.csv", "header");
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
  conversionCoefficientNeuronCoordinatesPerPixelXAxis = (xmax-xmin)/width;
  conversionCoefficientNeuronCoordinatesPerPixelYAxis = (ymax-ymin)/height;
}

public void drawSignals() {
  Table signalsForThisFrame = loadTable(frame+"_allSignals.csv", "header");
  for (TableRow signal : signalsForThisFrame.rows()) {
    drawSignal(signal);
  }
}

public void drawSignal(TableRow signal) {
  //TODO: Need a way to draw differently for different kinds of signals. Probably will have to include this info in csv. 
}
