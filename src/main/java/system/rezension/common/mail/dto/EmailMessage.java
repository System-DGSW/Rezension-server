package system.rezension.common.mail.dto;

public record EmailMessage(
        String to,
        String subject,
        String content
) {
}
