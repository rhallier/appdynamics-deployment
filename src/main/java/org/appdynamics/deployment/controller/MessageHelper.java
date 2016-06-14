package org.appdynamics.deployment.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public final class MessageHelper {

	private MessageHelper() {
	}

	public static void addFlashSuccess(RedirectAttributes ra, String message, Object... args) {
		addFlashAttribute(ra, message, Message.Type.SUCCESS, args);
	}

	public static void addFlashError(RedirectAttributes ra, String message, Object... args) {
		addFlashAttribute(ra, message, Message.Type.DANGER, args);
	}

	public static void addFlashInfo(RedirectAttributes ra, String message, Object... args) {
		addFlashAttribute(ra, message, Message.Type.INFO, args);
	}

	public static void addFlashWarning(RedirectAttributes ra, String message, Object... args) {
		addFlashAttribute(ra, message, Message.Type.WARNING, args);
	}

	private static void addFlashAttribute(RedirectAttributes ra, String message, Message.Type type, Object... args) {
		ra.addFlashAttribute(Message.MESSAGE_ATTRIBUTE, new Message(message, type, args));
	}

	public static void addAdditionalFlashSuccess(RedirectAttributes ra, String message, Object... args) {
		addAdditionalFlashAttribute(ra, message, Message.Type.SUCCESS, args);
	}

	public static void addAdditionalFlashError(RedirectAttributes ra, String message, Object... args) {
		addAdditionalFlashAttribute(ra, message, Message.Type.DANGER, args);
	}

	public static void addAdditionalFlashInfo(RedirectAttributes ra, String message, Object... args) {
		addAdditionalFlashAttribute(ra, message, Message.Type.INFO, args);
	}

	public static void addAdditionalFlashWarning(RedirectAttributes ra, String message, Object... args) {
		addAdditionalFlashAttribute(ra, message, Message.Type.WARNING, args);
	}

	private static void addAdditionalFlashAttribute(RedirectAttributes ra, String message, Message.Type type, Object... args) {
		ra.addFlashAttribute(Message.MESSAGE_ADDITIONAL_ATTRIBUTE, new Message(message, type, args));
	}

	public static void addSuccess(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.SUCCESS, args);
	}

	public static void addSuccess(Model model, String message) {
		addAttribute(model, message, Message.Type.SUCCESS);
	}

	public static void addError(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.DANGER, args);
	}

	public static void addError(Model model, String message) {
		addAttribute(model, message, Message.Type.DANGER);
	}

	public static void addInfo(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.INFO, args);
	}

	public static void addWarning(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.WARNING, args);
	}

	private static void addAttribute(Model model, String message, Message.Type type, Object... args) {
		model.addAttribute(Message.MESSAGE_ATTRIBUTE, new Message(message, type, args));
	}
}