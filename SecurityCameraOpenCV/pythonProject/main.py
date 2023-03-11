import os
from flask import Flask,render_template,Response
import cv2
app=Flask(__name__)
from pygame import mixer
mixer.init()
audio_file = os.path.dirname(__file__) + '/fart.mp3'


def frames():
    sound = mixer.Sound('siuu.wav')
    static_back = None
    video = cv2.VideoCapture(0)
    reset = 0
    while True:
        check, frame = video.read()
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        gray = cv2.GaussianBlur(gray, (21, 21), 0)
        reset += 1
        if static_back is None or reset % 10 == 0:
            static_back = gray
            continue

        diff_frame = cv2.absdiff(static_back, gray)
        thresh_frame = cv2.threshold(diff_frame, 30, 255, cv2.THRESH_BINARY)[1]
        thresh_frame = cv2.dilate(thresh_frame, None, iterations=2)

        # Finding contour of moving object
        cnts, _ = cv2.findContours(thresh_frame.copy(),
                                   cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

        for contour in cnts:
            if cv2.contourArea(contour) < 5000:
                continue

            (x, y, w, h) = cv2.boundingRect(contour)
            cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 3)
            sound.play()


        #cv2.imshow("Gray Frame", gray)
        #cv2.imshow("Difference Frame", diff_frame)
        #cv2.imshow("Threshold Frame", thresh_frame)
        cv2.imshow("Color Frame", frame)

        #cv2.imshow("Reference", static_back)
        if not check:
            break
        else:
            ret,buffer=cv2.imencode('.jpg',frame)
        webFrame = buffer.tobytes()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + webFrame + b'\r\n')

        key = cv2.waitKey(1)
        if key == ord('q'):
            break

@app.route('/')
def index():
    return render_template('index.html')
@app.route('/video')
def video():
    return Response(frames(),mimetype='multipart/x-mixed-replace; boundary=frame')
if __name__=="__main__":
    app.run(debug=True)

cv2.destroyAllWindows()
