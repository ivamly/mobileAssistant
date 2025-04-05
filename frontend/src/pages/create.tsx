import React from "react";
import {
  Card,
  CardBody,
  CardHeader,
  Input,
  Button,
  Progress,
} from "@heroui/react";
import DefaultLayout from "@/layouts/default";
import { Icon } from "@iconify/react";
export default function Create() {
  const [file, setFile] = React.useState<File | null>(null);
  const [uploadProgress, setUploadProgress] = React.useState(0);
  const [isUploading, setIsUploading] = React.useState(false);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
      setUploadProgress(0);
    }
  };

  const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!file) return;

    setIsUploading(true);

    const interval = setInterval(() => {
      setUploadProgress((prev) => {
        if (prev >= 100) {
          clearInterval(interval);
          setIsUploading(false);
          return 100;
        }
        return prev + 10;
      });
    }, 500);

    console.log("File details:", {
      name: file.name,
      type: file.type,
      size: `${(file.size / 1024 / 1024).toFixed(2)} MB`,
    });

    setTimeout(() => {
      clearInterval(interval);
      setIsUploading(false);
      setUploadProgress(0);
      setFile(null);

      e.target.reset();
    }, 5500);
  };

  return (
    <DefaultLayout>
      <div className="text-2xl font-semibold mb-10 flex justify-between">
        <div>Новое пространство</div>
      </div>

      <Card className="w-full max-w-md">
        <CardHeader className="flex gap-3">
          <Icon icon="lucide:upload" className="text-xl" />
          <div className="flex flex-col">
            <p className="text-md">File Upload</p>
            <p className="text-small text-default-500">
              Upload video or audio files
            </p>
          </div>
        </CardHeader>
        <CardBody>
          <form onSubmit={handleSubmit} className="space-y-4">
            <Input
              type="file"
              accept="video/*,audio/*"
              onChange={handleFileChange}
              description="Supported formats: MP4, MP3, WAV, etc."
              isDisabled={isUploading}
            />

            {file && (
              <div className="space-y-2">
                <p className="text-small">
                  Selected file: {file.name}
                  <span className="ml-2 text-default-500">
                    ({(file.size / 1024 / 1024).toFixed(2)} MB)
                  </span>
                </p>
              </div>
            )}

            {isUploading && (
              <Progress
                aria-label="Upload progress"
                value={uploadProgress}
                className="max-w-md"
                color="primary"
              />
            )}

            <Button
              type="submit"
              color="primary"
              startContent={<Icon icon="lucide:upload" />}
              isLoading={isUploading}
              isDisabled={!file || isUploading}
              fullWidth
            >
              Upload File
            </Button>
          </form>
        </CardBody>
      </Card>
    </DefaultLayout>
  );
}
