import os
from PIL import Image

def resize_image(image_path, max_length=756):
    with Image.open(image_path) as img:
        width, height = img.size
        if width > max_length or height > max_length:
            if width > height:
                new_height = int(max_length * (height / width))
                new_size = (max_length, new_height)
            else:
                new_width = int(max_length * (width / height))
                new_size = (new_width, max_length)
            img = img.resize(new_size, resample=Image.LANCZOS)  # Using LANCZOS resampling filter
            img = img.convert("RGB")  # Convert to RGB mode before saving as JPEG
            img.save(image_path, "JPEG")

def process_image_caption_pairs(root_dir):
    for subdir, dirs, files in os.walk(root_dir):
        for file in files:
            if file.lower().endswith('.jpg'):
                image_path = os.path.join(subdir, file)
                caption_path = os.path.splitext(image_path)[0] + '.txt'

                # Check if the image file size is less than 50kb
                if os.path.getsize(image_path) < 50 * 1024:
                    os.remove(image_path)  # Delete the image
                    if os.path.exists(caption_path):
                        os.remove(caption_path)  # Delete the caption if it exists
                else:
                    resize_image(image_path)  # Resize the image if necessary


# Replace 'your_directory_path' with the path to the directory containing the image-caption pairs
process_image_caption_pairs('.')