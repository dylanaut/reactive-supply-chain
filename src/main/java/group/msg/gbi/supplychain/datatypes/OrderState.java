package group.msg.gbi.supplychain.datatypes;

/**
 * Transitionen:
 * Created → Confirmed: Auftrag validiert und bestätigt.
 * Confirmed → Processing: Bearbeitung des Auftrags gestartet.
 * Processing → Ready for Shipment: Alle Artikel sind verfügbar und kommissioniert.
 * Ready for Shipment → Shipped: Versandinformationen erstellt und Ware versandt.
 * Shipped → Completed: Lieferung bestätigt und Zahlung eingegangen.
 * Jeder Zustand → Cancelled: Auftrag storniert durch Kunde/System.
 * Jeder Zustand → Problematic: Ein Problem tritt auf (z. B. Zahlung fehlgeschlagen).
 * <p>
 * [Created] --> [Confirmed] --> [Processing] --> [Ready for Shipment] --> [Shipped] --> [Completed]
 * |               |                |                   |                     |
 * v               v                v                   v                     v
 * [Cancelled]    [Cancelled]    [Problematic]       [Problematic]        [Problematic]
 */

public enum OrderState {
    CREATED("Der Auftrag wird erstellt und enthält alle grundlegenden Informationen (z. B. Kunde, Produkte, Mengen, Preise)."),
    CONFIRMED("Der Auftrag wurde geprüft und vom Kunden oder System bestätigt."),
    PROCESSING("Der Auftrag wird bearbeitet, z. B. durch Lagerprüfung, Kommissionierung oder Produktionsvorbereitung."),
    READY_FOR_SHIPMENT("Der Auftrag ist vollständig bearbeitet und bereit für den Versand."),
    SHIPPED("Die Ware wurde versandt, und der Kunde wurde informiert."),
    COMPLETED("Der Auftrag wurde erfolgreich abgeschlossen, und die Zahlung ist eingegangen."),
    PROBLEMATIC("Es ist ein Problem aufgetreten (z. B. Lagerbestand nicht verfügbar, Zahlung fehlgeschlagen)."),
    CANCELLED("Der Auftrag wurde storniert und ist nicht mehr aktiv.");

    private String description;


    OrderState(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
