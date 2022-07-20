package core;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  19.07.2022
//--------------------------------------------------//
public class Controller {

    private static Controller instance = null;

    public static Controller get() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void receivedCount(int sensorId, int count) {
        int conflictingRequestsCounter = 0;
        TrackSectionEnterRequest validRequest = null;
        TrackSectionEnterRequest possiblyInvalidRequest = null;
        boolean isIngoing = false;

        switch(sensorId) {
            case 1:
                // INGOING
                    // D|H --> C
                possiblyInvalidRequest = checkForRequest(ETrackSection.D, ETrackSection.C);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.H, ETrackSection.C);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }

                // OUTGOING
                    // C --> D|H
                possiblyInvalidRequest = checkForRequest(ETrackSection.C, ETrackSection.D);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.C, ETrackSection.H);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 2:
                // INGOING
                    // C --> D
                possiblyInvalidRequest = checkForRequest(ETrackSection.C, ETrackSection.D);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // D --> C
                possiblyInvalidRequest = checkForRequest(ETrackSection.D, ETrackSection.C);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 3:
                // INGOING
                    // C --> H
                possiblyInvalidRequest = checkForRequest(ETrackSection.C, ETrackSection.H);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // H --> C
                possiblyInvalidRequest = checkForRequest(ETrackSection.H, ETrackSection.C);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 4:
                // INGOING
                    // E --> D
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.D);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // D --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.D, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 5:
                // INGOING
                    // E --> H
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.H);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // H --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.H, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 6:
                // INGOING
                    // D|H --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.D, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.H, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // E --> D|H
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.D);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.H);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 7:
                // INGOING
                    // F|I --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.F, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.I, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // E --> F|I
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.F);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.I);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 8:
                // INGOING
                    // E --> F
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.F);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // F --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.F, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 9:
                // INGOING
                    // E --> I
                possiblyInvalidRequest = checkForRequest(ETrackSection.E, ETrackSection.I);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // I --> E
                possiblyInvalidRequest = checkForRequest(ETrackSection.I, ETrackSection.E);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 10:
                // INGOING
                    // A --> F
                possiblyInvalidRequest = checkForRequest(ETrackSection.A, ETrackSection.F);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // F --> A
                possiblyInvalidRequest = checkForRequest(ETrackSection.F, ETrackSection.A);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 11:
                // INGOING
                    // A --> I
                possiblyInvalidRequest = checkForRequest(ETrackSection.A, ETrackSection.I);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // I --> A
                possiblyInvalidRequest = checkForRequest(ETrackSection.I, ETrackSection.A);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 12:
                // INGOING
                    // F|I --> A
                possiblyInvalidRequest = checkForRequest(ETrackSection.F, ETrackSection.A);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.I, ETrackSection.A);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // A --> F|I
                possiblyInvalidRequest = checkForRequest(ETrackSection.A, ETrackSection.F);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }

                possiblyInvalidRequest = checkForRequest(ETrackSection.A, ETrackSection.I);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 13:
                // INGOING
                    // B --> A
                possiblyInvalidRequest = checkForRequest(ETrackSection.B, ETrackSection.A);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // A --> B
                possiblyInvalidRequest = checkForRequest(ETrackSection.A, ETrackSection.B);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 14:
                // INGOING
                    // B --> G
                possiblyInvalidRequest = checkForRequest(ETrackSection.B, ETrackSection.G);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // G --> B
                possiblyInvalidRequest = checkForRequest(ETrackSection.G, ETrackSection.B);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                break;
            case 15:
                // INGOING
                    // B --> C
                possiblyInvalidRequest = checkForRequest(ETrackSection.B, ETrackSection.C);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // C --> B
                possiblyInvalidRequest = checkForRequest(ETrackSection.C, ETrackSection.B);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = false;
                }
                break;
            case 16:
                // INGOING
                    // B --> G
                possiblyInvalidRequest = checkForRequest(ETrackSection.B, ETrackSection.G);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                // OUTGOING
                    // G --> B
                possiblyInvalidRequest = checkForRequest(ETrackSection.G, ETrackSection.B);
                if(possiblyInvalidRequest != null) {
                    conflictingRequestsCounter++;
                    validRequest = possiblyInvalidRequest;
                    isIngoing = true;
                }
                break;
        }
        if(conflictingRequestsCounter == 0) {
            // THROW ERROR - TRAIN SHOULD NOT MOVE WHEN THERE IS NO __VALID__ REQUEST
            System.err.println("No valid request found: Sensor#" + sensorId);
        } else if(conflictingRequestsCounter == 1) {
            // everything works as it should
            TrackSectionEnterRequest.advanceRequest(validRequest, isIngoing, count);

        } else {
            // THROW ERROR - THERE SHOULD NOT BE MULTIPLE __VALID__ REQUESTS FOR THE SAME TRACK SWITCH
            System.err.println("Multiple valid requests: Sensor#" + sensorId);
        }
    }

    // checks if request exists, but only returns if it's also valid
    private TrackSectionEnterRequest checkForRequest(ETrackSection from, ETrackSection to) {
        TrackSectionEnterRequest req = TrackSectionEnterRequest.getRequest(from, to);
        if(req != null) {
            if(req.isPermitted()) return req;
        }
        return null;
    }
}
